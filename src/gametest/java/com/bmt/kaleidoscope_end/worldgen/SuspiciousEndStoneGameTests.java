package com.bmt.kaleidoscope_end.worldgen;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.init.KEBlocks;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

@SuppressWarnings("all")
public class SuspiciousEndStoneGameTests {
    @GameTest(maxTicks = 200)
    public void generatesInTheEnd(GameTestHelper helper) {
        ServerLevel end = helper.getLevel().getServer().getLevel(Level.END);
        helper.assertTrue(end != null, Component.literal("End dimension must exist"));
        var generator = end.getChunkSource().getGenerator();
        helper.assertTrue(generator instanceof NoiseBasedChunkGenerator,
                Component.literal("End worldgen tests require the normal End generator, not the flat GameTest preset"));
        var placedFeatures = end.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE);
        for (var biome : end.registryAccess().lookupOrThrow(Registries.BIOME).getOrThrow(BiomeTags.IS_END)) {
            for (String name : new String[]{"suspicious_end_stone_small", "suspicious_end_stone_large", "suspicious_end_stone_buried"}) {
                var key = ResourceKey.create(Registries.PLACED_FEATURE, KaleidoscopeEnd.id(name));
                helper.assertTrue(generator.getBiomeGenerationSettings(biome).hasFeature(placedFeatures.getOrThrow(key).value()),
                        Component.literal("Missing " + name + " in biome " + biome.getRegisteredName()));
            }
        }
        int found = 0;
        int endStone = 0;
        int exposed = 0;
        int missingEntities = 0;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int chunkX = -4; chunkX <= 4; chunkX++) {
            for (int chunkZ = -4; chunkZ <= 4; chunkZ++) {
                LevelChunk chunk = end.getChunk(chunkX, chunkZ);
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = 0; y <= 90; y++) {
                            pos.set(chunkX * 16 + x, y, chunkZ * 16 + z);
                            var state = chunk.getBlockState(pos);
                            if (state.is(Blocks.END_STONE)) {
                                endStone++;
                            }
                            if (state.is(KEBlocks.SUSPICIOUS_END_STONE)) {
                                found++;
                                if (end.isEmptyBlock(pos.above())) {
                                    exposed++;
                                }
                                if (!(end.getBlockEntity(pos) instanceof BrushableBlockEntity)) {
                                    missingEntities++;
                                }
                            }
                        }
                    }
                }
            }
        }
        KaleidoscopeEnd.LOGGER.info("End worldgen test: blocks={}, exposedAbove={}, missingEntities={}, endStone={}",
                found, exposed, missingEntities, endStone);
        helper.assertTrue(endStone > 0, Component.literal("Sampled chunks must contain End terrain"));
        helper.assertTrue(found > 0, Component.literal("Suspicious end stone must generate in new End chunks"));
        helper.assertTrue(missingEntities == 0, Component.literal("Generated brushable blocks need block entities"));
        helper.succeed();
    }
}

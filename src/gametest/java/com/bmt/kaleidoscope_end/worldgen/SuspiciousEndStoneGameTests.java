package com.bmt.kaleidoscope_end.worldgen;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.init.KEBlocks;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;

public class SuspiciousEndStoneGameTests {
    @GameTest(maxTicks = 200)
    public void generatesInTheEnd(GameTestHelper helper) {
        ServerLevel end = helper.getLevel().getServer().getLevel(Level.END);
        helper.assertTrue(end != null, Component.literal("End dimension must exist"));
        int found = 0;
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
                            if (chunk.getBlockState(pos).is(KEBlocks.SUSPICIOUS_END_STONE)) {
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
        KaleidoscopeEnd.LOGGER.info("End worldgen test: blocks={}, exposedAbove={}, missingEntities={}",
                found, exposed, missingEntities);
        helper.assertTrue(found > 0, Component.literal("Suspicious end stone must generate in new End chunks"));
        helper.assertTrue(missingEntities == 0, Component.literal("Generated brushable blocks need block entities"));
        helper.succeed();
    }
}

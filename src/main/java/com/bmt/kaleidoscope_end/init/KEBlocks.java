package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.block.SuspiciousDragonEggBlock;
import com.bmt.kaleidoscope_end.block.crops.DreamBerryHeadBlock;
import com.bmt.kaleidoscope_end.block.crops.DreamBerryPlantBlock;
import com.bmt.kaleidoscope_end.block.crops.EnderMint;
import com.github.ysbbbbbb.kaleidoscopecookery.block.kitchen.StoveBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public final class KEBlocks {
    public static final Supplier<BlockBehaviour.Properties> CROP_DEFAULT_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
    public static final Supplier<BlockBehaviour.Properties> CAVE_VINES_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY);

    public static final DreamBerryPlantBlock DREAM_BERRY_PLANT = new DreamBerryPlantBlock(CAVE_VINES_PROPERTIES.get());
    public static final DreamBerryHeadBlock DREAM_BERRY_HEAD = new DreamBerryHeadBlock(CAVE_VINES_PROPERTIES.get());
    public static final EnderMint ENDER_MINT = new EnderMint(CROP_DEFAULT_PROPERTIES.get());
    public static final Block SUSPICIOUS_END_STONE = new BrushableBlock(
            Blocks.END_STONE,
            SoundEvents.BRUSH_SAND,
            SoundEvents.BRUSH_SAND_COMPLETED,
            BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(SoundType.SUSPICIOUS_SAND).pushReaction(PushReaction.DESTROY)
    ) {
        @Override
        public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
            BrushableBlockEntity blockEntity = (BrushableBlockEntity) super.newBlockEntity(pos, state);
            if (blockEntity != null) {
                blockEntity.setLootTable(ResourceKey.create(Registries.LOOT_TABLE, KaleidoscopeEnd.id("archaeology/suspicious_end_stone")), 0L);
            }
            return blockEntity;
        }
    };
    public static final SuspiciousDragonEggBlock SUSPICIOUS_DRAGON_EGG = new SuspiciousDragonEggBlock(
            Blocks.DRAGON_EGG,
            SoundEvents.BRUSH_SAND,
            SoundEvents.BRUSH_SAND_COMPLETED,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F).lightLevel(state -> 1).noOcclusion().pushReaction(PushReaction.DESTROY)
    );
    public static final StoveBlock END_STOVE = new StoveBlock();

    private KEBlocks() {
    }

    public static void registerBlocks() {
        Registry.register(BuiltInRegistries.BLOCK, KaleidoscopeEnd.id("dream_berry_plant"), DREAM_BERRY_PLANT);
        Registry.register(BuiltInRegistries.BLOCK, KaleidoscopeEnd.id("dream_berry_head"), DREAM_BERRY_HEAD);
        Registry.register(BuiltInRegistries.BLOCK, KaleidoscopeEnd.id("ender_mint"), ENDER_MINT);
        Registry.register(BuiltInRegistries.BLOCK, KaleidoscopeEnd.id("suspicious_end_stone"), SUSPICIOUS_END_STONE);
        Registry.register(BuiltInRegistries.BLOCK, KaleidoscopeEnd.id("suspicious_dragon_egg"), SUSPICIOUS_DRAGON_EGG);
        Registry.register(BuiltInRegistries.BLOCK, KaleidoscopeEnd.id("end_stove"), END_STOVE);
    }
}

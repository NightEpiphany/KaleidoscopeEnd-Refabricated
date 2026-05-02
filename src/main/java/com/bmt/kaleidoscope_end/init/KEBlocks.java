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

import java.util.function.Function;
import java.util.function.Supplier;

public final class KEBlocks {
    public static final Supplier<BlockBehaviour.Properties> CROP_DEFAULT_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
    public static final Supplier<BlockBehaviour.Properties> CAVE_VINES_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).randomTicks().noCollision().instabreak().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY);

    public static final DreamBerryPlantBlock DREAM_BERRY_PLANT = register("dream_berry_plant", DreamBerryPlantBlock::new, CAVE_VINES_PROPERTIES.get());
    public static final DreamBerryHeadBlock DREAM_BERRY_HEAD = register("dream_berry_head", DreamBerryHeadBlock::new, CAVE_VINES_PROPERTIES.get());
    public static final EnderMint ENDER_MINT = register("ender_mint", EnderMint::new, CROP_DEFAULT_PROPERTIES.get());
    public static final Block SUSPICIOUS_END_STONE = register("suspicious_end_stone", properties -> new BrushableBlock(
            Blocks.END_STONE,
            SoundEvents.BRUSH_SAND,
            SoundEvents.BRUSH_SAND_COMPLETED,
            properties
    ) {
        @Override
        public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
            BrushableBlockEntity blockEntity = (BrushableBlockEntity) super.newBlockEntity(pos, state);
            if (blockEntity != null) {
                blockEntity.setLootTable(ResourceKey.create(Registries.LOOT_TABLE, KaleidoscopeEnd.id("archaeology/suspicious_end_stone")), 0L);
            }
            return blockEntity;
        }
    }, BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(SoundType.SUSPICIOUS_SAND).pushReaction(PushReaction.DESTROY));
    public static final SuspiciousDragonEggBlock SUSPICIOUS_DRAGON_EGG = register("suspicious_dragon_egg",
            properties -> new SuspiciousDragonEggBlock(
                    Blocks.DRAGON_EGG,
                    SoundEvents.BRUSH_SAND,
                    SoundEvents.BRUSH_SAND_COMPLETED,
                    properties
            ),
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3.0F, 9.0F).lightLevel(state -> 1).noOcclusion().pushReaction(PushReaction.DESTROY)
    );
    public static final StoveBlock END_STOVE = register("end_stove",
            StoveBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)
                    .randomTicks()
                    .strength(1.5F, 6.0F)
    );

    private KEBlocks() {
    }

    private static <T extends Block> T register(String name, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties properties) {
        ResourceKey<Block> resourceKey = ResourceKey.create(Registries.BLOCK, KaleidoscopeEnd.id(name));
        T block = factory.apply(properties.setId(resourceKey));
        Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
        return block;
    }

    public static void registerBlocks() {
        // 由静态字段完成注册，保留空方法作为统一入口。
    }
}

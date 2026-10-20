package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.block.entity.DragonEggShellBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class KEBlockEntities {
    public static final BlockEntityType<DragonEggShellBlockEntity> DRAGON_EGG_SHELL = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            KaleidoscopeEnd.id("dragon_egg_shell"),
            FabricBlockEntityTypeBuilder.create(DragonEggShellBlockEntity::new, KEBlocks.DRAGON_EGG_SHELL).build()
    );

    private KEBlockEntities() {
    }

    public static void registerBlockEntities() {
    }
}

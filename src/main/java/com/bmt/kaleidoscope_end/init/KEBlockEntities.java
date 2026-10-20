package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.block.entity.DragonEggShellBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;

public final class KEBlockEntities {
    public static final BlockEntityType<DragonEggShellBlockEntity> DRAGON_EGG_SHELL = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            KaleidoscopeEnd.id("dragon_egg_shell"),
            new BlockEntityType<>(DragonEggShellBlockEntity::new, Set.of(KEBlocks.DRAGON_EGG_SHELL))
    );

    private KEBlockEntities() {
    }

    public static void registerBlockEntities() {
    }
}

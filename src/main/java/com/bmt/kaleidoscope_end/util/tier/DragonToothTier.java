package com.bmt.kaleidoscope_end.util.tier;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ToolMaterial;

public final class DragonToothTier {
    public static final ToolMaterial INSTANCE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2031,
            8.0F,
            8.0F,
            15,
            TagKey.create(Registries.ITEM, com.bmt.kaleidoscope_end.KaleidoscopeEnd.id("dragon_tooth_tool_materials"))
    );

    private DragonToothTier() {
    }
}

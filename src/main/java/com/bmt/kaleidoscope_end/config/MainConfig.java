package com.bmt.kaleidoscope_end.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class MainConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<List<? extends String>> DRAGON_TOOTH_KNIFE_EXTRA_END_MOBS = BUILDER
            .comment(
                    "List of additional mobs treated as End mobs by the Dragon Tooth Knife.",
                    "Format: namespace:path"
            )
            .defineList(
                    "dragon_tooth_knife.extraEndMobs",
                    List.of(
                            "minecraft:enderman",
                            "minecraft:endermite",
                            "minecraft:shulker",
                            "minecraft:ender_dragon"
                    ),
                    value -> value instanceof String
            );

    public static final ModConfigSpec.DoubleValue VOID_SHOT_CHANCE = BUILDER
            .comment("Chance for Void Shot to deal multiplied projectile damage.")
            .defineInRange("voidShotChance", 0.15D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue VOID_SHOT_BASE_MULTIPLIER = BUILDER
            .comment("Base damage multiplier for Void Shot; enchantment level is added to this value.")
            .defineInRange("voidShotBaseMultiplier", 1.0D, 1.0D, 100.0D);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private MainConfig() {
    }

    public static ModConfigSpec init() {
        return SPEC;
    }
}

package com.bmt.kaleidoscope_end.worldgen;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.tags.BiomeTags.IS_END;

public final class KEWorldgen {
    private static final ResourceKey<PlacedFeature> DREAM_BERRY = ResourceKey.create(Registries.PLACED_FEATURE, KaleidoscopeEnd.id("dream_berry"));
    private static final ResourceKey<PlacedFeature> ENDER_MINT = ResourceKey.create(Registries.PLACED_FEATURE, KaleidoscopeEnd.id("ender_mint"));
    private static final ResourceKey<PlacedFeature> SUSPICIOUS_END_STONE_SMALL = ResourceKey.create(Registries.PLACED_FEATURE, KaleidoscopeEnd.id("suspicious_end_stone_small"));
    private static final ResourceKey<PlacedFeature> SUSPICIOUS_END_STONE_LARGE = ResourceKey.create(Registries.PLACED_FEATURE, KaleidoscopeEnd.id("suspicious_end_stone_large"));
    private static final ResourceKey<PlacedFeature> SUSPICIOUS_END_STONE_BURIED = ResourceKey.create(Registries.PLACED_FEATURE, KaleidoscopeEnd.id("suspicious_end_stone_buried"));

    private KEWorldgen() {
    }

    public static void register() {
        BiomeModifications.addFeature(BiomeSelectors.tag(IS_END), GenerationStep.Decoration.UNDERGROUND_DECORATION, DREAM_BERRY);
        BiomeModifications.addFeature(BiomeSelectors.tag(IS_END), GenerationStep.Decoration.VEGETAL_DECORATION, ENDER_MINT);
        BiomeModifications.addFeature(BiomeSelectors.tag(IS_END), GenerationStep.Decoration.UNDERGROUND_ORES, SUSPICIOUS_END_STONE_SMALL);
        BiomeModifications.addFeature(BiomeSelectors.tag(IS_END), GenerationStep.Decoration.UNDERGROUND_ORES, SUSPICIOUS_END_STONE_LARGE);
        BiomeModifications.addFeature(BiomeSelectors.tag(IS_END), GenerationStep.Decoration.UNDERGROUND_ORES, SUSPICIOUS_END_STONE_BURIED);
    }
}

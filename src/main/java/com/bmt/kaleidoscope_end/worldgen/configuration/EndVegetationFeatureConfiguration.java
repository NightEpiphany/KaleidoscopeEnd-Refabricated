package com.bmt.kaleidoscope_end.worldgen.configuration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record EndVegetationFeatureConfiguration(int tries, int xzSpread, int ySpread, Holder<PlacedFeature> feature) {
    public static final MapCodec<EndVegetationFeatureConfiguration> CODEC = RecordCodecBuilder.mapCodec((config) -> config.group(
            ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(128).forGetter(EndVegetationFeatureConfiguration::tries),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("xz_spread").orElse(7).forGetter(EndVegetationFeatureConfiguration::xzSpread),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("y_spread").orElse(3).forGetter(EndVegetationFeatureConfiguration::ySpread),
            PlacedFeature.CODEC.fieldOf("feature").forGetter(EndVegetationFeatureConfiguration::feature)
    ).apply(config, EndVegetationFeatureConfiguration::new));
}

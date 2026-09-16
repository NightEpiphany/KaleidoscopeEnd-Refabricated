package com.bmt.kaleidoscope_end.worldgen.feature;

import com.bmt.kaleidoscope_end.worldgen.configuration.EndVegetationFeatureConfiguration;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.chunk.ChunkGenerator;
import org.jspecify.annotations.NonNull;

import java.util.stream.Stream;

public record EndVegetationFeature(EndVegetationFeatureConfiguration config) implements Feature {
    public static final MapCodec<EndVegetationFeature> CODEC =
            EndVegetationFeatureConfiguration.CODEC.xmap(EndVegetationFeature::new, EndVegetationFeature::config);

    @Override
    public @NonNull MapCodec<EndVegetationFeature> codec() {
        return CODEC;
    }

    @Override
    public @NonNull Stream<Holder<Feature>> getSubFeatures() {
        return config.feature().value().getFeatures();
    }

    @Override
    public boolean place(@NonNull WorldGenLevel level, @NonNull ChunkGenerator chunkGenerator,
                         @NonNull RandomSource random, @NonNull BlockPos origin) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        int successes = 0;
        int xzSpread = config.xzSpread() + 1;
        int ySpread = config.ySpread() + 1;

        for (int attempt = 0; attempt < config.tries(); attempt++) {
            mutablePos.setWithOffset(
                    origin,
                    random.nextInt(xzSpread) - random.nextInt(xzSpread),
                    random.nextInt(ySpread) - random.nextInt(ySpread),
                    random.nextInt(xzSpread) - random.nextInt(xzSpread)
            );

            if (config.feature().value().place(level, chunkGenerator, random, mutablePos)) {
                ++successes;
            }
        }
        return successes > 0;
    }
}

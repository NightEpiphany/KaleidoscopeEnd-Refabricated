package com.bmt.kaleidoscope_end.worldgen.feature;

import com.bmt.kaleidoscope_end.worldgen.configuration.EndVegetationFeatureConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.jspecify.annotations.NonNull;

public class EndVegetationFeature extends Feature<EndVegetationFeatureConfiguration> {
    public EndVegetationFeature(Codec<EndVegetationFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(@NonNull FeaturePlaceContext<EndVegetationFeatureConfiguration> context) {
        EndVegetationFeatureConfiguration config = context.config();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
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

            if (config.feature().value().place(level, context.chunkGenerator(), random, mutablePos)) {
                ++successes;
            }
        }
        return successes > 0;
    }
}

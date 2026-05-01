package com.bmt.kaleidoscope_end.datagen;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.init.KETags;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacements {

    public static final ResourceKey<PlacedFeature> SUSPICIOUS_END_STONE_SMALL = createKey("suspicious_end_stone_small");
    public static final ResourceKey<PlacedFeature> SUSPICIOUS_END_STONE_LARGE = createKey("suspicious_end_stone_large");
    public static final ResourceKey<PlacedFeature> SUSPICIOUS_END_STONE_BURIED = createKey("suspicious_end_stone_buried");
    public static final ResourceKey<PlacedFeature> ENDER_MINT = createKey("ender_mint");
    public static final ResourceKey<PlacedFeature> DREAM_BERRY = createKey("dream_berry");

    public static ResourceKey<PlacedFeature> createKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, KaleidoscopeEnd.id(key));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifier1) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifier1, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int tryCount, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(tryCount), placementModifier);
    }

    private static List<PlacementModifier> rareOrePlacement(PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(9), placementModifier);
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> holder = holdergetter.getOrThrow(ModFeatures.SUSPICIOUS_END_STONE_SMALL);
        Holder<ConfiguredFeature<?, ?>> holder1 = holdergetter.getOrThrow(ModFeatures.SUSPICIOUS_END_STONE_LARGE);
        Holder<ConfiguredFeature<?, ?>> holder2 = holdergetter.getOrThrow(ModFeatures.SUSPICIOUS_END_STONE_BURIED);

        PlacementUtils.register(context, SUSPICIOUS_END_STONE_SMALL, holder, commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(80))));
        PlacementUtils.register(context, SUSPICIOUS_END_STONE_LARGE, holder1, rareOrePlacement(HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(80))));
        PlacementUtils.register(context, SUSPICIOUS_END_STONE_BURIED, holder2, commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(80))));

        PlacementUtils.register(
                context,
                DREAM_BERRY,
                holdergetter.getOrThrow(ModFeatures.DREAM_BERRY),
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(50),
                PlacementUtils.FULL_RANGE,
                CountPlacement.of(50),
                InSquarePlacement.spread(),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.allOf(BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.matchesTag(KETags.Blocks.END_STONE_GROWABLE)), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1))
        );

        PlacementUtils.register(
                context,
                ENDER_MINT,
                holdergetter.getOrThrow(ModFeatures.ENDER_MINT),
                BiomeFilter.biome(),
                PlacementUtils.HEIGHTMAP,
                RarityFilter.onAverageOnceEvery(100)
        );
    }
}

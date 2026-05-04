package com.bmt.kaleidoscope_end.datagen;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.init.KEBlocks;
import com.bmt.kaleidoscope_end.init.KETags;
import com.bmt.kaleidoscope_end.worldgen.KEWorldgen;
import com.bmt.kaleidoscope_end.worldgen.configuration.EndVegetationFeatureConfiguration;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class ModFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> SUSPICIOUS_END_STONE_SMALL = createKey("suspicious_end_stone_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SUSPICIOUS_END_STONE_LARGE = createKey("suspicious_end_stone_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SUSPICIOUS_END_STONE_BURIED = createKey("suspicious_end_stone_buried");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ENDER_MINT = createKey("ender_mint");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DREAM_BERRY = createKey("dream_berry");


    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, KaleidoscopeEnd.id(name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest ruleTest = new BlockMatchTest(Blocks.END_STONE);
        List<OreConfiguration.TargetBlockState> list2 = List.of(OreConfiguration.target(ruleTest, KEBlocks.SUSPICIOUS_END_STONE.defaultBlockState()));

        FeatureUtils.register(context, SUSPICIOUS_END_STONE_SMALL, Feature.ORE, new OreConfiguration(list2, 4, 0.5F));
        FeatureUtils.register(context, SUSPICIOUS_END_STONE_LARGE, Feature.ORE, new OreConfiguration(list2, 12, 0.7F));
        FeatureUtils.register(context, SUSPICIOUS_END_STONE_BURIED, Feature.ORE, new OreConfiguration(list2, 8, 1.0F));

        RandomizedIntStateProvider dreamBerryHead = new RandomizedIntStateProvider(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(KEBlocks.DREAM_BERRY_HEAD.defaultBlockState(), 4)
                        .add(KEBlocks.DREAM_BERRY_HEAD.defaultBlockState().setValue(CaveVines.BERRIES, true), 1)
                        .build()),
                CaveVinesBlock.AGE,
                UniformInt.of(23, 25)
        );
        WeightedStateProvider dreamBerryPlant = new WeightedStateProvider(WeightedList.<BlockState>builder()
                .add(KEBlocks.DREAM_BERRY_PLANT.defaultBlockState(), 1)
                .add(KEBlocks.DREAM_BERRY_PLANT.defaultBlockState().setValue(CaveVines.BERRIES, true), 1)
                .build());


        FeatureUtils.register(
                context,
                DREAM_BERRY,
                Feature.BLOCK_COLUMN,
                new BlockColumnConfiguration(List.of(
                        BlockColumnConfiguration.layer(
                                new WeightedListInt(WeightedList.<IntProvider>builder()
                                        .add(UniformInt.of(0, 19), 1)
                                        .add(UniformInt.of(0, 2), 3)
                                        .add(UniformInt.of(0, 6), 10)
                                        .build()), dreamBerryPlant
                        )
                        , BlockColumnConfiguration.layer(ConstantInt.of(1), dreamBerryHead)
                ),
                        Direction.DOWN,
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        true
                )
        );

        Holder<PlacedFeature> enderMintPatch = PlacementUtils.inlinePlaced(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(KEBlocks.ENDER_MINT.defaultBlockState().setValue(CropBlock.AGE, 7))),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesTag(new Vec3i(0, -1, 0), KETags.Blocks.END_STONE_GROWABLE)
                ))
        );

        FeatureUtils.register(
                context,
                ENDER_MINT,
                KEWorldgen.END_VEGETATION.get(),
                new EndVegetationFeatureConfiguration(96, 7, 3, enderMintPatch)
        );

    }
}

package com.bmt.kaleidoscope_end.datagen;

import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;

public class ModFeatureUtils {
    public static BlockPredicate simplePatchPredicate(TagKey<Block> blocks) {
        BlockPredicate blockpredicate;
        blockpredicate = BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesTag(Direction.DOWN.getUnitVec3i(), blocks));
        return blockpredicate;
    }

    public static BlockPredicate notAirPredicate(Direction direction) {
        BlockPredicate blockpredicate;
        blockpredicate = BlockPredicate.not(BlockPredicate.matchesBlocks(direction.getUnitVec3i(), Blocks.AIR));
        return blockpredicate;
    }

}

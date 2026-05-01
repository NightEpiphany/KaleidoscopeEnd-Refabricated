package com.bmt.kaleidoscope_end.block.crops;

import com.bmt.kaleidoscope_end.init.KEItem;
import com.bmt.kaleidoscope_end.init.KETags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class EnderMint extends KECropBlockBase {
    public EnderMint(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(KETags.Blocks.END_STONE_GROWABLE);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getRawBrightness(pos, 0) >= 9) {
            int age = getAge(state);
            if (age < getMaxAge()) {
                float growthSpeed = getGrowthSpeed(this, level, pos);
                if (random.nextInt((int) (25.0F / growthSpeed) + 1) == 0) {
                    level.setBlock(pos, getStateForAge(age + 1), 2);
                }
            }
        }
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return KEItem.ENDER_MINT;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        return mayPlaceOn(level.getBlockState(belowPos), level, belowPos);
    }
}

package com.bmt.kaleidoscope_end.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class DragonDustItem extends BoneMealItem {

    public DragonDustItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(context.getClickedFace());

        if (applyDragonDust(context.getItemInHand(), level, blockpos, context.getPlayer())) {
            if (!level.isClientSide) {
                level.levelEvent(1505, blockpos, 0);
            }

            Player player = context.getPlayer();
            if (player != null) {
                player.getCooldowns().addCooldown(this, 100);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            BlockState blockstate = level.getBlockState(blockpos);
            boolean flag = blockstate.isFaceSturdy(level, blockpos, context.getClickedFace());
            if (flag && growWaterPlantWithoutConsume(context.getItemInHand(), level, blockpos1, context.getClickedFace())) {
                if (!level.isClientSide) {
                    level.levelEvent(1505, blockpos1, 0);
                }

                Player player = context.getPlayer();
                if (player != null) {
                    player.getCooldowns().addCooldown(this, 100);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    @SuppressWarnings("unused")
    public static boolean applyDragonDust(ItemStack stack, Level level, BlockPos pos, @Nullable Player player) {
        BlockState blockstate = level.getBlockState(pos);

        if (blockstate.getBlock() instanceof BonemealableBlock bonemealableblock) {
            if (bonemealableblock.isValidBonemealTarget(level, pos, blockstate)) {
                if (level instanceof ServerLevel) {
                    if (bonemealableblock.isBonemealSuccess(level, level.random, pos, blockstate)) {
                        bonemealableblock.performBonemeal((ServerLevel)level, level.random, pos, blockstate);
                    }
                }
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unused")
    public static boolean growWaterPlantWithoutConsume(ItemStack stack, Level level, BlockPos pos, @Nullable Direction direction) {
        if (level.getBlockState(pos).is(Blocks.WATER) && level.getFluidState(pos).getAmount() == 8) {
            if (level instanceof ServerLevel serverlevel) {
                RandomSource randomsource = level.getRandom();

                label78:
                for (int i = 0; i < 128; ++i) {
                    BlockPos blockpos = pos;
                    BlockState blockstate = Blocks.SEAGRASS.defaultBlockState();

                    for (int j = 0; j < i / 16; ++j) {
                        blockpos = blockpos.offset(randomsource.nextInt(3) - 1, (randomsource.nextInt(3) - 1) * randomsource.nextInt(3) / 2, randomsource.nextInt(3) - 1);
                        if (level.getBlockState(blockpos).isCollisionShapeFullBlock(level, blockpos)) {
                            continue label78;
                        }
                    }

                    Holder<Biome> holder = level.getBiome(blockpos);
                    if (holder.is(BiomeTags.PRODUCES_CORALS_FROM_BONEMEAL)) {
                        if (i == 0 && direction != null && direction.getAxis().isHorizontal()) {
                            blockstate = BuiltInRegistries.BLOCK.getTag(BlockTags.WALL_CORALS).flatMap((p_204098_) -> p_204098_.getRandomElement(level.random)).map((p_204100_) -> p_204100_.value().defaultBlockState()).orElse(blockstate);
                            if (blockstate.hasProperty(BaseCoralWallFanBlock.FACING)) {
                                blockstate = blockstate.setValue(BaseCoralWallFanBlock.FACING, direction);
                            }
                        } else if (randomsource.nextInt(4) == 0) {
                            blockstate = BuiltInRegistries.BLOCK.getTag(BlockTags.UNDERWATER_BONEMEALS).flatMap((p_204091_) -> p_204091_.getRandomElement(level.random)).map((p_204095_) -> p_204095_.value().defaultBlockState()).orElse(blockstate);
                        }
                    }

                    if (blockstate.is(BlockTags.WALL_CORALS, (p_204093_) -> p_204093_.hasProperty(BaseCoralWallFanBlock.FACING))) {
                        for (int k = 0; !blockstate.canSurvive(level, blockpos) && k < 4; ++k) {
                            blockstate = blockstate.setValue(BaseCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(randomsource));
                        }
                    }

                    if (blockstate.canSurvive(level, blockpos)) {
                        BlockState blockstate1 = level.getBlockState(blockpos);
                        if (blockstate1.is(Blocks.WATER) && level.getFluidState(blockpos).getAmount() == 8) {
                            level.setBlock(blockpos, blockstate, 3);
                        } else if (blockstate1.is(Blocks.SEAGRASS) && randomsource.nextInt(10) == 0) {
                            ((BonemealableBlock) Blocks.SEAGRASS).performBonemeal(serverlevel, randomsource, blockpos, blockstate1);
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
}

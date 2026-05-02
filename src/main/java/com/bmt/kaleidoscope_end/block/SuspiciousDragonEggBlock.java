package com.bmt.kaleidoscope_end.block;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SuspiciousDragonEggBlock extends BrushableBlock implements Fallable {
    private static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public SuspiciousDragonEggBlock(Block turnsInto, SoundEvent brushSound, SoundEvent brushCompletedSound, Properties properties) {
        super(turnsInto, brushSound, brushCompletedSound, properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        level.scheduleTick(pos, this, getDelayAfterPlace());
    }

    @Override
    public @NotNull BlockState updateShape(
            BlockState state,
            LevelReader levelReader,
            ScheduledTickAccess scheduledTickAccess,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            RandomSource randomSource
    ) {
        scheduledTickAccess.scheduleTick(pos, this, getDelayAfterPlace());
        return super.updateShape(state, levelReader, scheduledTickAccess, pos, direction, neighborPos, neighborState, randomSource);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (isFree(level.getBlockState(pos.below())) && pos.getY() >= level.getMinY()) {
            FallingBlockEntity.fall(level, pos, state);
        }
    }

    public static boolean isFree(BlockState state) {
        return state.isAir() || state.is(BlockTags.FIRE) || state.liquid() || state.canBeReplaced();
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        teleport(state, level, pos);
    }

    private void teleport(BlockState state, Level level, BlockPos pos) {
        WorldBorder worldBorder = level.getWorldBorder();
        for (int i = 0; i < 1000; ++i) {
            BlockPos targetPos = pos.offset(level.random.nextInt(16) - level.random.nextInt(16), level.random.nextInt(8) - level.random.nextInt(8), level.random.nextInt(16) - level.random.nextInt(16));
            if (level.getBlockState(targetPos).isAir() && worldBorder.isWithinBounds(targetPos)) {
                if (level.isClientSide()) {
                    for (int j = 0; j < 128; ++j) {
                        double progress = level.random.nextDouble();
                        float xSpeed = (level.random.nextFloat() - 0.5F) * 0.2F;
                        float ySpeed = (level.random.nextFloat() - 0.5F) * 0.2F;
                        float zSpeed = (level.random.nextFloat() - 0.5F) * 0.2F;
                        double x = Mth.lerp(progress, targetPos.getX(), pos.getX()) + (level.random.nextDouble() - 0.5D) + 0.5D;
                        double y = Mth.lerp(progress, targetPos.getY(), pos.getY()) + level.random.nextDouble() - 0.5D;
                        double z = Mth.lerp(progress, targetPos.getZ(), pos.getZ()) + (level.random.nextDouble() - 0.5D) + 0.5D;
                        level.addParticle(ParticleTypes.PORTAL, x, y, z, xSpeed, ySpeed, zSpeed);
                    }
                } else {
                    level.setBlock(targetPos, state, 2);
                    level.removeBlock(pos, false);
                }
                return;
            }
        }
    }

    protected int getDelayAfterPlace() {
        return 5;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        BrushableBlockEntity blockEntity = (BrushableBlockEntity) super.newBlockEntity(pos, state);
        if (blockEntity != null) {
            blockEntity.setLootTable(ResourceKey.create(Registries.LOOT_TABLE, KaleidoscopeEnd.id("archaeology/suspicious_dragon_egg")), 0L);
        }
        return blockEntity;
    }
}

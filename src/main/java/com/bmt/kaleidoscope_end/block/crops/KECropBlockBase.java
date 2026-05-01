package com.bmt.kaleidoscope_end.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class KECropBlockBase extends CropBlock {
    public KECropBlockBase(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(state)) {
            getHandHarvestItem(state, level, pos, null, player, ItemStack.EMPTY);
            level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlock(pos, getStateForAge(5), 2);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public Set<Item> getHandHarvestExclude() {
        return Set.of();
    }

    public void getHandHarvestItem(BlockState state, Level level, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack tool) {
        if (level instanceof ServerLevel serverLevel) {
            getDrops(state, serverLevel, pos, blockEntity, entity, tool).forEach(itemStack -> {
                if (!getHandHarvestExclude().contains(itemStack.getItem())) {
                    popResource(level, pos, itemStack);
                }
            });
            state.spawnAfterBreak(serverLevel, pos, tool, true);
        }
    }
}

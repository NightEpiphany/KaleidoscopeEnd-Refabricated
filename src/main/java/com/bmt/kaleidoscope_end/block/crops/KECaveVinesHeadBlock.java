package com.bmt.kaleidoscope_end.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Supplier;

public class KECaveVinesHeadBlock extends CaveVinesBlock {
    private final Supplier<? extends Block> bodyBlock;
    private final Supplier<Item> fruitItem;

    public KECaveVinesHeadBlock(Properties properties, Supplier<? extends Block> bodyBlock, Supplier<Item> fruitItem) {
        super(properties);
        this.bodyBlock = bodyBlock;
        this.fruitItem = fruitItem;
    }

    @Override
    protected Block getBodyBlock() {
        return bodyBlock.get();
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(fruitItem.get());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!state.getValue(BERRIES)) {
            return InteractionResult.PASS;
        }
        Block.popResource(level, pos, new ItemStack(fruitItem.get()));
        float pitch = Mth.randomBetween(level.random, 0.8F, 1.2F);
        level.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, pitch);
        BlockState newState = state.setValue(BERRIES, Boolean.FALSE);
        level.setBlock(pos, newState, 2);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
        return InteractionResult.SUCCESS;
    }
}

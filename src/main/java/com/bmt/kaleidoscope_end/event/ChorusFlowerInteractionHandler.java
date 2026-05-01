package com.bmt.kaleidoscope_end.event;

import com.bmt.kaleidoscope_end.init.KEItem;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public final class ChorusFlowerInteractionHandler {
    private ChorusFlowerInteractionHandler() {
    }

    public static void register() {
        UseBlockCallback.EVENT.register(ChorusFlowerInteractionHandler::onUseBlock);
    }

    private static InteractionResult onUseBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        BlockPos pos = hitResult.getBlockPos();
        BlockState state = level.getBlockState(pos);
        ItemStack heldItem = player.getItemInHand(hand);
        if (heldItem.getItem() != Items.SHEARS || !state.is(Blocks.CHORUS_FLOWER)) {
            return InteractionResult.PASS;
        }

        if (!player.isCreative()) {
            EquipmentSlot slot = hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
            heldItem.hurtAndBreak(1, player, slot);
        }

        BlockState plantState = Blocks.CHORUS_PLANT.defaultBlockState();
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            BlockState neighborState = level.getBlockState(neighborPos);
            BooleanProperty property = getConnectionProperty(direction);
            boolean shouldConnect = neighborState.is(Blocks.CHORUS_FLOWER) || neighborState.is(Blocks.CHORUS_PLANT);
            plantState = plantState.setValue(property, shouldConnect);
        }

        level.setBlock(pos, plantState, 3);
        if (level instanceof ServerLevel serverLevel) {
            ItemStack petals = new ItemStack(KEItem.CHORUS_PETAL, 2);
            ItemEntity itemEntity = new ItemEntity(serverLevel, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, petals);
            itemEntity.setDefaultPickUpDelay();
            serverLevel.addFreshEntity(itemEntity);
        }

        level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.PLAYERS, 1.0F, 1.0F);
        return InteractionResult.SUCCESS;
    }

    private static BooleanProperty getConnectionProperty(Direction direction) {
        return switch (direction) {
            case DOWN -> ChorusPlantBlock.DOWN;
            case UP -> ChorusPlantBlock.UP;
            case NORTH -> ChorusPlantBlock.NORTH;
            case SOUTH -> ChorusPlantBlock.SOUTH;
            case WEST -> ChorusPlantBlock.WEST;
            case EAST -> ChorusPlantBlock.EAST;
        };
    }
}

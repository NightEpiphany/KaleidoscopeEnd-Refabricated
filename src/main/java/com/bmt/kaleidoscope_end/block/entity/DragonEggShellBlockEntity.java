package com.bmt.kaleidoscope_end.block.entity;

import com.bmt.kaleidoscope_end.init.KEBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Prediction;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DragonEggShellBlockEntity extends BlockEntity implements Container {
    private static final int SLOT_COUNT = 8;
    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);

    public DragonEggShellBlockEntity(BlockPos pos, BlockState state) {
        super(KEBlockEntities.DRAGON_EGG_SHELL, pos, state);
    }

    public void putOn(ItemStack stack) {
        if (stack.getItem().canFitInsideContainerItems() && addItem(stack)) {
            if (level != null) {
                level.playSound(null, worldPosition, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
            }
        }
    }

    public void takeOut(Player player) {
        for (int slot = 0; slot < getContainerSize(); slot++) {
            ItemStack extracted = removeItem(slot, getMaxStackSize());
            if (extracted.isEmpty()) {
                continue;
            }

            if (!player.getInventory().add(extracted)) {
                player.drop(extracted, false, Prediction.SERVER_ONLY);
            }
            if (level != null) {
                level.playSound(null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS);
            }
            return;
        }
    }

    private boolean addItem(ItemStack stack) {
        int originalCount = stack.getCount();
        int maxCount = Math.min(getMaxStackSize(), stack.getMaxStackSize());

        for (int slot = 0; slot < getContainerSize() && !stack.isEmpty(); slot++) {
            ItemStack existing = getItem(slot);
            if (!existing.isEmpty() && ItemStack.isSameItemSameComponents(existing, stack)) {
                int moved = Math.min(maxCount - existing.getCount(), stack.getCount());
                if (moved > 0) {
                    existing.grow(moved);
                    stack.shrink(moved);
                }
            }
        }

        for (int slot = 0; slot < getContainerSize() && !stack.isEmpty(); slot++) {
            if (!getItem(slot).isEmpty()) {
                continue;
            }
            items.set(slot, stack.split(maxCount));
        }

        if (stack.getCount() == originalCount) {
            return false;
        }
        setChanged();
        return true;
    }

    @Override
    public int getContainerSize() {
        return SLOT_COUNT;
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack stack = ContainerHelper.removeItem(items, slot, amount);
        if (!stack.isEmpty()) {
            setChanged();
        }
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        items.set(slot, stack);
        if (stack.getCount() > getMaxStackSize(stack)) {
            stack.setCount(getMaxStackSize(stack));
        }
        setChanged();
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return stack.getItem().canFitInsideContainerItems();
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        for (int slot = 0; slot < SLOT_COUNT; slot++) {
            items.set(slot, ItemStack.EMPTY);
        }
        setChanged();
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}

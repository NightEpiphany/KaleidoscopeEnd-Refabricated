package com.bmt.kaleidoscope_end.item;

import com.bmt.kaleidoscope_end.init.KEItem;
import com.github.ysbbbbbb.kaleidoscopecookery.api.item.IHasContainer;
import com.github.ysbbbbbb.kaleidoscopecookery.item.FoodWithEffectsItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DragonEggShellFoodItem extends FoodWithEffectsItem implements IHasContainer {
    public DragonEggShellFoodItem(FoodProperties properties) {
        super(properties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        ItemStack itemStack = super.finishUsingItem(stack, level, entity);
        ItemStack dragonEggShell = new ItemStack(KEItem.DRAGON_EGG_SHELL);
        if (itemStack.isEmpty()) {
            return dragonEggShell;
        }
        if (entity instanceof Player player) {
            player.getInventory().placeItemBackInInventory(dragonEggShell);
        } else {
            level.addFreshEntity(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), dragonEggShell));
        }
        return itemStack;
    }

    @Override
    public Item getContainerItem() {
        return KEItem.DRAGON_EGG_SHELL;
    }
}

package com.bmt.kaleidoscope_end.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public final class DragonEggShellBlockItem extends BlockItem {
    public DragonEggShellBlockItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(
            @NonNull ItemStack stack,
            Item.@NonNull TooltipContext context,
            @NonNull TooltipDisplay display,
            Consumer<Component> tooltip,
            @NonNull TooltipFlag flag
    ) {
        tooltip.accept(Component.translatable("tooltip.kaleidoscope_end.dragon_egg_shell").withStyle(ChatFormatting.DARK_GRAY));
    }
}

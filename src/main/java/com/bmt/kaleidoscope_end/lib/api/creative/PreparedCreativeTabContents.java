package com.bmt.kaleidoscope_end.lib.api.creative;

import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Set;

public record PreparedCreativeTabContents(
        List<ItemStack> displayItems,
        Set<ItemStack> searchItems,
        CreativeTabLayout layout
) {
}

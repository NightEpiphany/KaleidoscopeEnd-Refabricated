package com.bmt.kaleidoscope_end.lib.mixins.client;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractContainerScreen.class)
public interface AbstractContainerScreenAccessor {
    @Accessor("leftPos")
    int kaleidoscope_end$getLeftPos();

    @Accessor("topPos")
    int kaleidoscope_end$getTopPos();
}

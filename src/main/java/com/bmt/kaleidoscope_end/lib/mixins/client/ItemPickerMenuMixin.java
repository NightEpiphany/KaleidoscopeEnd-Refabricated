package com.bmt.kaleidoscope_end.lib.mixins.client;

import com.bmt.kaleidoscope_end.lib.client.CreativeSectionRenderer;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.ItemPickerMenu.class)
public abstract class ItemPickerMenuMixin {
    @Shadow
    protected abstract int getRowIndexForScroll(float scrollOffs);

    @Inject(method = "scrollTo", at = @At("HEAD"))
    private void kaleidoscope_end$recordScroll(float scrollOffs, CallbackInfo ci) {
        CreativeSectionRenderer.updateScroll(
                (CreativeModeInventoryScreen.ItemPickerMenu) (Object) this,
                getRowIndexForScroll(scrollOffs)
        );
    }
}

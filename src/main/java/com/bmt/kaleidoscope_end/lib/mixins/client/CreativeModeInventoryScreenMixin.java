package com.bmt.kaleidoscope_end.lib.mixins.client;

import com.bmt.kaleidoscope_end.lib.client.CreativeSectionRenderer;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin {
    @Shadow
    private static CreativeModeTab selectedTab;

    @Shadow
    private EditBox searchBox;

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void kaleidoscope_end$renderSectionBanners(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float partialTick,
            CallbackInfo ci
    ) {
        CreativeSectionRenderer.render(
                (CreativeModeInventoryScreen) (Object) this,
                graphics,
                selectedTab,
                searchBox,
                ((AbstractContainerScreenAccessor) this).kaleidoscope_end$getLeftPos(),
                ((AbstractContainerScreenAccessor) this).kaleidoscope_end$getTopPos()
        );
    }
}

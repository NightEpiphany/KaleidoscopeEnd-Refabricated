package com.bmt.kaleidoscope_end.lib.mixins;

import com.bmt.kaleidoscope_end.lib.api.creative.CreativeTabSections;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Set;

@Mixin(CreativeModeTab.class)
public abstract class CreativeModeTabMixin {
    @Shadow
    @Mutable
    private Collection<ItemStack> displayItems;

    @Shadow
    @Mutable
    private Set<ItemStack> displayItemsSearchTab;

    @Inject(method = "buildContents", at = @At("TAIL"))
    private void kaleidoscope_end$applySectionLayout(CreativeModeTab.ItemDisplayParameters parameters, CallbackInfo ci) {
        CreativeTabSections.PreparedContents prepared = CreativeTabSections.prepare(
                (CreativeModeTab) (Object) this,
                displayItems,
                displayItemsSearchTab
        );
        displayItems = prepared.displayItems();
        displayItemsSearchTab = prepared.searchItems();
    }
}

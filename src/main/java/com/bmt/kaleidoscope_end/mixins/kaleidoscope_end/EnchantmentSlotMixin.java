package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.world.inventory.EnchantmentMenu$3")
public abstract class EnchantmentSlotMixin {

    @Unique
    private static final TagKey<Item> EXTRA_FUEL =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("fabric", "enchanting_fuels"));

    @Redirect(
            method = "mayPlace",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private boolean kaleidoscope$allowExtra(ItemStack stack, Item item) {
        if (stack.is(item)) return true;
        return stack.is(EXTRA_FUEL);
    }
}

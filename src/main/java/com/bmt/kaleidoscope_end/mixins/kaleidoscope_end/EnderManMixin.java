package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end;

import com.bmt.kaleidoscope_end.api.event.EnderManAngerEvent;
import com.bmt.kaleidoscope_end.init.KEEvents;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnderMan.class)
public class EnderManMixin {
    @WrapOperation(method = "isLookingAtMe", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean kaleidoscope$invalidLook(ItemStack instance, Item item, Operation<Boolean> original, @Local(argsOnly = true) Player player) {
        var event = new EnderManAngerEvent((EnderMan) (Object) this, player);
        KEEvents.STARE_ENDERMAN.invoker().onStare(event);
        return original.call(instance, item) || event.isCanceled();
    }
}

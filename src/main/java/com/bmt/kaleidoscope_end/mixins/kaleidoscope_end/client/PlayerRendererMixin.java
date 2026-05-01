package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end.client;

import com.bmt.kaleidoscope_end.init.KEEffects;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {
    @WrapOperation(method = "setupRotations*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;isInWater()Z"))
    private boolean kaleidoscope_end$isInWater(AbstractClientPlayer instance, Operation<Boolean> original) {
        if (instance.hasEffect(KEEffects.DREAM)) {
            return true;
        }
        return original.call(instance);
    }

    @Unique
    @Deprecated
    //@WrapOperation(method = "setupRotations*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;isVisuallySwimming()Z"))
    private boolean kaleidoscope_end$isSwimming(AbstractClientPlayer instance, Operation<Boolean> original) {
        if (instance.hasEffect(KEEffects.DREAM)) {
            return true;
        }
        return original.call(instance);
    }
}

package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end;

import com.bmt.kaleidoscope_end.init.KEEffects;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "isInWater", at = @At("RETURN"), cancellable = true)
    private void kaleidoscope_end$isInWater(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof Player player && player.hasEffect(KEEffects.DREAM)) {
            cir.setReturnValue(true);
        }
    }

    @WrapOperation(method = "updateSwimming", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isInWater()Z"))
    private boolean kaleidoscope_end$isInWater(Entity instance, Operation<Boolean> original) {
        if ((Object) this instanceof Player player && player.hasEffect(KEEffects.DREAM)) {
            return true;
        }
        return original.call(instance);
    }

    @WrapOperation(method = "updateSwimming", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isUnderWater()Z"))
    private boolean kaleidoscope_end$isUnderWater(Entity instance, Operation<Boolean> original) {
        if ((Object) this instanceof Player player && player.hasEffect(KEEffects.DREAM)) {
            return true;
        }
        return original.call(instance);
    }

    @WrapOperation(method = "updateSwimming", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean kaleidoscope_end$isDreamFluid(FluidState instance, TagKey<Fluid> tagKey, Operation<Boolean> original) {
        if ((Object) this instanceof Player player && player.hasEffect(KEEffects.DREAM)) {
            return true;
        }
        return original.call(instance, tagKey);
    }

    @WrapOperation(method = "isVisuallyCrawling", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isInWater()Z"))
    private boolean kaleidoscope_end$isVisuallyInWater(Entity instance, Operation<Boolean> original) {
        if ((Object) this instanceof Player player && player.hasEffect(KEEffects.DREAM)) {
            return true;
        }
        return original.call(instance);
    }
}

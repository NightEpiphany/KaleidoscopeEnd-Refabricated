package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.bmt.kaleidoscope_end.init.KEEffects;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = {"getDamageAfterArmorAbsorb", "getDamageAfterMagicAbsorb"}, at = @At("HEAD"), cancellable = true)
    private void onGetDamageAfterArmorAbsorb(DamageSource damageSource, float damage, CallbackInfoReturnable<Float> cir) {
        if (damageSource.getEntity() instanceof LivingEntity living && living.hasEffect(KEEffects.VOID_EROSION)) {
            cir.setReturnValue(damage);
        }
    }
}

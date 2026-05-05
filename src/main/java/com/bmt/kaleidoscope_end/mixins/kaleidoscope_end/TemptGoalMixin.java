package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.monster.Endermite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TemptGoal.class)
public class TemptGoalMixin {
    @WrapOperation(method = "canUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;getAttributeValue(Lnet/minecraft/core/Holder;)D"))
    private double kaleidoscope_end$canUse(Mob instance, Holder<Attribute> holder, Operation<Double> original) {
        if (instance instanceof Endermite) {
            return 4.0f;
        }
        return original.call(instance, holder);
    }
}

package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end.client;

import com.bmt.kaleidoscope_end.init.KEEffects;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends Player {
    public LocalPlayerMixin(Level level, BlockPos blockPos, float rotation, GameProfile gameProfile) {
        super(level, blockPos, rotation, gameProfile);
    }

    @WrapOperation(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUnderWater()Z", ordinal = 0))
    private boolean kaleidoscope_end$isUnderWaterStartSprint(LocalPlayer instance, Operation<Boolean> original) {
        if (this.hasEffect(KEEffects.DREAM)) {
            return true;
        }
        return original.call(instance);
    }

    @WrapOperation(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUnderWater()Z", ordinal = 1))
    private boolean kaleidoscope_end$isUnderWaterKeepSprint(LocalPlayer instance, Operation<Boolean> original) {
        if (this.hasEffect(KEEffects.DREAM)) {
            return true;
        }
        return original.call(instance);
    }

    @WrapOperation(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUnderWater()Z", ordinal = 2))
    private boolean kaleidoscope_end$isUnderWaterSprintCollision(LocalPlayer instance, Operation<Boolean> original) {
        if (this.hasEffect(KEEffects.DREAM)) {
            return true;
        }
        return original.call(instance);
    }
}

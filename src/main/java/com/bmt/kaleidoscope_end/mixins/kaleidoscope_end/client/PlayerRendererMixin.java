package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end.client;

import com.bmt.kaleidoscope_end.init.KEEffects;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(AvatarRenderer.class)
public abstract class PlayerRendererMixin {
    @ModifyVariable(method = "setupRotations(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;FF)V", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    private AvatarRenderState kaleidoscope_end$setupRotations(AvatarRenderState avatarRenderState) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect(KEEffects.DREAM)) {
            avatarRenderState.isInWater = true;
        }
        return avatarRenderState;
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

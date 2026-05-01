package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end;

import com.bmt.kaleidoscope_end.common.KEEndermiteInfo;
import com.bmt.kaleidoscope_end.api.IEndermiteExtension;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.jetbrains.annotations.Nullable;

@Mixin(NearestAttackableTargetGoal.class)
public abstract class NearestAttackableTargetGoalMixin extends TargetGoal {
    @Shadow
    @Nullable
    protected LivingEntity target;

    public NearestAttackableTargetGoalMixin(Mob p_26140_, boolean p_26141_) {
        super(p_26140_, p_26141_);
    }

    @Inject(method = "findTarget", at = @At("RETURN"))
    private void findTarget(CallbackInfo ci) {
        if (this.mob instanceof Endermite endermite) {
            KEEndermiteInfo info = IEndermiteExtension.getInfo(endermite);
            if (info.fed && target instanceof Player) {
                target = null;
            }
        }
    }

}

package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end;

import com.bmt.kaleidoscope_end.common.KEEndermiteInfo;
import com.bmt.kaleidoscope_end.api.IEndermiteExtension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Endermite.class)
public abstract class EndermiteMixin extends Monster implements IEndermiteExtension {
    @Shadow
    protected abstract void registerGoals();

    @Unique
    private final KEEndermiteInfo ke$endermiteInfo = new KEEndermiteInfo((((Endermite) (Object) this)));

    protected EndermiteMixin(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    @Inject(method = "registerGoals", at = @At("RETURN"))
    private void registerGoals(CallbackInfo ci) {
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.AMETHYST_SHARD), false));
    }

    @Inject(method = "addAdditionalSaveData", at = @At("RETURN"))
    public void addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.put("ke_endermite_info", ke$endermiteInfo.serializeNBT());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
    public void readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        ke$endermiteInfo.deserializeNBT(compound.getCompound("ke_endermite_info"));
    }

    @Inject(method = "aiStep", at = @At("RETURN"))
    private void aiStep(CallbackInfo ci) {
        ke$endermiteInfo.aiStep();
    }

    public KEEndermiteInfo ke$getEndermiteInfo() {
        return ke$endermiteInfo;
    }
}
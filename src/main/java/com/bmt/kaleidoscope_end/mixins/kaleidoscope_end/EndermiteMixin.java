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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Endermite.class)
@Implements(@Interface(iface = IEndermiteExtension.class, prefix = "$kaleidoscope_end$"))
public abstract class EndermiteMixin extends Monster implements IEndermiteExtension {
    @Unique
    private static final String NBT_KEY = "ke_endermite_info";
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
    public void addAdditionalSaveData(ValueOutput valueOutput, CallbackInfo ci) {
        valueOutput.store(NBT_KEY, CompoundTag.CODEC, ke$endermiteInfo.serializeNBT());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
    public void readAdditionalSaveData(ValueInput valueInput, CallbackInfo ci) {
        valueInput.childOrEmpty(NBT_KEY).read(NBT_KEY, CompoundTag.CODEC).ifPresent(ke$endermiteInfo::deserializeNBT);
    }

    @Inject(method = "aiStep", at = @At("RETURN"))
    private void aiStep(CallbackInfo ci) {
        ke$endermiteInfo.aiStep();
    }

    public KEEndermiteInfo ke$getEndermiteInfo() {
        return ke$endermiteInfo;
    }
}

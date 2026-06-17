package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;

public final class KEEffects {
    public static Holder<MobEffect> DREAM;
    public static Holder<MobEffect> VOID_EROSION;
    public static Holder<MobEffect> MINT;

    private KEEffects() {
    }

    public static void registerEffects() {
        DREAM = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id("dream"), new MobEffect(MobEffectCategory.BENEFICIAL, 0xFFFFFF) {
            @Override
            public boolean applyEffectTick(@NonNull ServerLevel serverLevel, @NonNull LivingEntity livingEntity, int amplifier) {
                livingEntity.fallDistance = 1.0F;
                return true;
            }

            @Override
            public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
                return true;
            }
        });
        VOID_EROSION = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id("void_erosion"), new MobEffect(MobEffectCategory.BENEFICIAL, 0x4B0082) {
        });
        MINT = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id("mint"), new MobEffect(MobEffectCategory.BENEFICIAL, 0xDA70D6) {
        });
    }

    private static Identifier id(String path) {
        return KaleidoscopeEnd.id(path);
    }
}

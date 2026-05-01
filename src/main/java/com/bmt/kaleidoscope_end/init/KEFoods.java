package com.bmt.kaleidoscope_end.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import org.jetbrains.annotations.Nullable;

public class KEFoods {
    public static final FoodProperties DRAGON_BREATH_CHORUS_SOUP = new FoodProperties.Builder()
            .nutrition(16)
            .saturationModifier(0.643f)
            .effect(createCookeryEffect("vigor", 480 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties STUFFED_SHULKER = new FoodProperties.Builder()
            .nutrition(13)
            .saturationModifier(0.615f)
            .effect(createCookeryEffect("satiated_shield", 90 * 20), 1.0F)
            .alwaysEdible().build();

    public static final FoodProperties CHORUS_PASTA = new FoodProperties.Builder()
            .nutrition(9)
            .saturationModifier(0.611f)
            .effect(createCookeryEffect("sulfur", 90 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties RAW_ENDER_DRAGON_MEAT = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.6f)
            .alwaysEdible().build();

    public static final FoodProperties COOKED_ENDER_DRAGON_MEAT = new FoodProperties.Builder()
            .nutrition(16)
            .saturationModifier(0.8f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 20, 0), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties DREAM_BERRY = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .effect(createEndEffect("dream", 30 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties MINT_CHORUS_MOUSSE = new FoodProperties.Builder()
            .nutrition(9)
            .saturationModifier(0.4f)
            .effect(createEndEffect("mint", 60 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties OPTIC_NERVE = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .alwaysEdible().build();

    public static final FoodProperties END_SALAD_ITEM = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(0.667F)
            .effect(createEndEffect("dream", 90 * 20), 1.0F)
            .build();

    public static final FoodProperties END_SALAD_BLOCK = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.667F)
            .effect(createEndEffect("dream", 100 * 20), 1.0F)
            .build();

    public static final FoodProperties DARK_DRAGON_EGG_STEW_ITEM = new FoodProperties.Builder()
            .nutrition(16)
            .saturationModifier(1.8F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 120 * 20, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 120 * 20, 1), 1.0f)
            .effect(createEndEffect("void_erosion", 30 * 20), 1.0F)
            .build();

    public static final FoodProperties DARK_DRAGON_EGG_STEW_BLOCK = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(1.8F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 130 * 20, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 130 * 20, 1), 1.0f)
            .effect(createEndEffect("void_erosion", 35 * 20), 1.0F)
            .build();

    public static final FoodProperties SHULKER_SHELL_MEAT = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.4f)
            .effect(new MobEffectInstance(MobEffects.LEVITATION, 3 * 20, 0), 0.5f)
            .alwaysEdible().build();

    public static final FoodProperties SHULKER_SHELL_STEW = new FoodProperties.Builder()
            .nutrition(9)
            .saturationModifier(0.8f)
            .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60 * 20, 0), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties SHULKER_ICE_CREAM = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.55f)
            .effect(createCookeryEffect("tundra_strider", 60 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties VOID_CONCH_NOODLE_SOUP = new FoodProperties.Builder()
            .nutrition(14)
            .saturationModifier(0.643f)
            .effect(createEndEffect("void_erosion", 10 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties STIR_FRIED_ENDERMITE_MEAT = new FoodProperties.Builder()
            .nutrition(9)
            .saturationModifier(0.611f)
            .effect(createCookeryEffect("vigor", 90 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties STIR_FRIED_ENDERMITE_MEAT_RICE_BOWL = new FoodProperties.Builder()
            .nutrition(14)
            .saturationModifier(0.643f)
            .effect(createCookeryEffect("satiated_shield", 180 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties RAW_ENDERMITE_MEAT = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .effect(new MobEffectInstance(MobEffects.POISON, 5 * 20, 0), 0.3f)
            .alwaysEdible().build();

    public static final FoodProperties ROASTED_ENDERMITE_MEAT = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.6f)
            .alwaysEdible().build();

    public static final FoodProperties END_CATERPILLAR = new FoodProperties.Builder()
            .nutrition(18)
            .saturationModifier(0.2f)
            .alwaysEdible().build();

    public static final FoodProperties DRAGON_BREATH_MIXED_STEW = new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(0.55f)
            .effect(createCookeryEffect("satiated_shield", 80 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties DRAGON_HEAD_WITH_SAUCE_ITEM  = new FoodProperties.Builder()
            .nutrition(24)
            .saturationModifier(0.8F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300 * 20, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1), 1.0f)
            .effect(createEndEffect("void_erosion", 35 * 20), 1.0F)
            .build();

    public static final FoodProperties DRAGON_HEAD_WITH_SAUCE_BLOCK = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.8F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300 * 20, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1), 1.0f)
            .effect(createEndEffect("void_erosion", 40 * 20), 1.0F)
            .build();
    public static final FoodProperties MINT_NOODLE_SOUP = new FoodProperties.Builder()
            .nutrition(14)
            .saturationModifier(0.643f)
            .effect(createEndEffect("mint", 180 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties MINT_SAUCE_SHULKER_MEAT = new FoodProperties.Builder()
            .nutrition(9)
            .saturationModifier(0.611f)
            .effect(createCookeryEffect("vigor", 90 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties MINT_SAUCE_SHULKER_MEAT_RICE_BOWL = new FoodProperties.Builder()
            .nutrition(14)
            .saturationModifier(0.643f)
            .effect(createCookeryEffect("satiated_shield", 180 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties ENDER_MINT_CANDY = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.4f)
            .effect(createEndEffect("mint", 15 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties DRAGON_SOUFFLE = new FoodProperties.Builder()
            .nutrition(9)
            .saturationModifier(0.8f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, -1, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, -1, 1), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties DARK_DRAGON_STEAK_ITEM  = new FoodProperties.Builder()
            .nutrition(24)
            .saturationModifier(0.8F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300 * 20, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1), 1.0f)
            .effect(createEndEffect("void_erosion", 35 * 20), 1.0F)
            .build();

    public static final FoodProperties DARK_DRAGON_STEAK_BLOCK = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.8F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300 * 20, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1), 1.0f)
            .effect(createEndEffect("void_erosion", 40 * 20), 1.0F)
            .build();
    public static final FoodProperties FRIED_DRAGON_EGG = new FoodProperties.Builder()
            .nutrition(18)
            .saturationModifier(0.8f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300 * 20, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300 * 20, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties DRAGON_EGG_CUSTARD_ITEM = new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(1.8F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300 * 20, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1), 1.0f)
            .effect(createEndEffect("void_erosion", 35 * 20), 1.0F)
            .build();

    public static final FoodProperties DRAGON_EGG_CUSTARD_BLOCK = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(1.8F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300 * 20, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1), 1.0f)
            .effect(createEndEffect("void_erosion", 40 * 20), 1.0F)
            .build();

    public static final FoodProperties CHORUS_FLOWER_TEA = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.667f)
            .effect(createCookeryEffect("preservation", 180 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties CHORUS_FLOWER_CAKE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.6f)
            .effect(createCookeryEffect("preservation", 120 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties CHORUS_SEED_COOKIE = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .effect(createEndEffect("dream", 30 * 20), 0.5f)
            .alwaysEdible().build();

    public static final FoodProperties DRAGON_EGG_LIQUID_FOOD = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.2f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 0), 0.3f)
            .alwaysEdible().build();

    public static final FoodProperties CHORUS_SEED_FOOD = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1f)
            .alwaysEdible().build();


    public static final FoodProperties DRAGON_EGG_ICE_CREAM = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.6f)
            .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60 * 20, 0), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties STUFFED_VOID_CONCH = new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(0.7f)
            .effect(createEndEffect("void_erosion", 30 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties OPTIC_NERVE_SWEET_AND_SOUR_PORK_ITEM = new FoodProperties.Builder()
            .nutrition(14)
            .saturationModifier(0.8F)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 480 * 20, 0), 1.0F)
            .alwaysEdible().build();

    public static final FoodProperties OPTIC_NERVE_SWEET_AND_SOUR_PORK_BLOCK = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.8F)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 480 * 20, 0), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties END_CATERPILLAR_SASHIMI = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(0.5f)
            .effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 120 * 20, 0), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties VOID_MUTTON_STEAK_ITEM = new FoodProperties.Builder()
            .nutrition(16)
            .saturationModifier(1.2F)
            .effect(createEndEffect("void_erosion", 180 * 20), 1.0f)
            .alwaysEdible().build();

    public static final FoodProperties VOID_MUTTON_STEAK_BLOCK = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(1.2F)
            .effect(createEndEffect("void_erosion", 30 * 20), 1.0f)
            .alwaysEdible().build();

    @Nullable
    private static MobEffectInstance createCookeryEffect(String path, int duration) {
        return createEffect("kaleidoscope_cookery", path, duration, 0);
    }

    @Nullable
    private static MobEffectInstance createEndEffect(String path, int duration) {
        return createEffect("kaleidoscope_end", path, duration, 0);
    }

    @Nullable
    private static MobEffectInstance createEffect(String namespace, String path, int duration, int amplifier) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(namespace, path);
        Holder<MobEffect> effect = BuiltInRegistries.MOB_EFFECT.getHolder(id).orElse(null);
        if (effect == null) return null;
        return new MobEffectInstance(effect, duration, amplifier);
    }

    public static void init() {
    }
}

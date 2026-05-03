package com.bmt.kaleidoscope_end.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface KEConsumables {
    Consumable DRAGON_EGG_ICE_CREAM_ITEM = buildConsumable(createVanillaEffect("strength", 300 * 20), createVanillaEffect("regeneration", 300 * 20), createEndEffect("void_erosion", 35 * 20));
    Consumable DRAGON_EGG_ICE_CREAM_BLOCK = buildConsumable(createVanillaEffect("strength", 300 * 20), createVanillaEffect("regeneration", 300 * 20), createEndEffect("void_erosion", 35 * 20));
    Consumable END_CATERPILLAR_SASHIMI_ITEM = buildConsumable(createVanillaEffect("water_breathing", 120 * 20));
    Consumable END_CATERPILLAR_SASHIMI_BLOCK = buildConsumable(createVanillaEffect("water_breathing", 120 * 20));

    Consumable DRAGON_BREATH_CHORUS_SOUP = buildConsumable(createCookeryEffect("vigor", 480 * 20));
    Consumable STUFFED_SHULKER = buildConsumable(createCookeryEffect("satiated_shield", 90 * 20));
    Consumable CHORUS_PASTA = buildConsumable(createCookeryEffect("sulfur", 90 * 20));
    Consumable RAW_ENDER_DRAGON_MEAT = buildConsumable();
    Consumable COOKED_ENDER_DRAGON_MEAT = buildConsumable(new MobEffectInstance(MobEffects.REGENERATION, 20 * 20, 0));
    Consumable DREAM_BERRY = buildConsumable(createEndEffect("dream", 30 * 20));
    Consumable MINT_CHORUS_MOUSSE = buildConsumable(createEndEffect("mint", 60 * 20));
    Consumable OPTIC_NERVE = buildConsumable();
    Consumable END_SALAD_ITEM = buildConsumable(createEndEffect("dream", 90 * 20));
    Consumable END_SALAD_BLOCK = buildConsumable(createEndEffect("dream", 100 * 20));
    Consumable DARK_DRAGON_EGG_STEW_ITEM = buildConsumable(
            new MobEffectInstance(MobEffects.STRENGTH, 120 * 20, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 120 * 20, 1),
            createEndEffect("void_erosion", 30 * 20)
    );
    Consumable DARK_DRAGON_EGG_STEW_BLOCK = buildConsumable(
            new MobEffectInstance(MobEffects.STRENGTH, 130 * 20, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 130 * 20, 1),
            createEndEffect("void_erosion", 35 * 20)
    );
    Consumable SHULKER_SHELL_MEAT = buildConsumable(0.5F, new MobEffectInstance(MobEffects.LEVITATION, 3 * 20, 0));
    Consumable SHULKER_SHELL_STEW = buildConsumable(new MobEffectInstance(MobEffects.SLOW_FALLING, 60 * 20, 0));
    Consumable SHULKER_ICE_CREAM = buildConsumable(createCookeryEffect("tundra_strider", 60 * 20));
    Consumable VOID_CONCH_NOODLE_SOUP = buildConsumable(createEndEffect("void_erosion", 10 * 20));
    Consumable STIR_FRIED_ENDERMITE_MEAT = buildConsumable(createCookeryEffect("vigor", 90 * 20));
    Consumable STIR_FRIED_ENDERMITE_MEAT_RICE_BOWL = buildConsumable(createCookeryEffect("satiated_shield", 180 * 20));
    Consumable RAW_ENDERMITE_MEAT = buildConsumable(0.3F, new MobEffectInstance(MobEffects.POISON, 5 * 20, 0));
    Consumable ROASTED_ENDERMITE_MEAT = buildConsumable();
    Consumable END_CATERPILLAR = buildConsumable();
    Consumable DRAGON_BREATH_MIXED_STEW = buildConsumable(createCookeryEffect("satiated_shield", 80 * 20));
    Consumable DRAGON_HEAD_WITH_SAUCE_ITEM = buildConsumable(
            new MobEffectInstance(MobEffects.STRENGTH, 300 * 20, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1),
            createEndEffect("void_erosion", 35 * 20)
    );
    Consumable DRAGON_HEAD_WITH_SAUCE_BLOCK = buildConsumable(
            new MobEffectInstance(MobEffects.STRENGTH, 300 * 20, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1),
            createEndEffect("void_erosion", 40 * 20)
    );
    Consumable MINT_NOODLE_SOUP = buildConsumable(createEndEffect("mint", 180 * 20));
    Consumable MINT_SAUCE_SHULKER_MEAT = buildConsumable(createCookeryEffect("vigor", 90 * 20));
    Consumable MINT_SAUCE_SHULKER_MEAT_RICE_BOWL = buildConsumable(createCookeryEffect("satiated_shield", 180 * 20));
    Consumable ENDER_MINT_CANDY = buildConsumable(createEndEffect("mint", 15 * 20));
    Consumable DRAGON_SOUFFLE = buildConsumable(
            new MobEffectInstance(MobEffects.STRENGTH, -1, 1),
            new MobEffectInstance(MobEffects.RESISTANCE, -1, 1),
            new MobEffectInstance(MobEffects.REGENERATION, -1, 1)
    );
    Consumable DARK_DRAGON_STEAK_ITEM = buildConsumable(
            new MobEffectInstance(MobEffects.STRENGTH, 300 * 20, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1),
            createEndEffect("void_erosion", 35 * 20)
    );
    Consumable DARK_DRAGON_STEAK_BLOCK = buildConsumable(
            new MobEffectInstance(MobEffects.STRENGTH, 300 * 20, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1),
            createEndEffect("void_erosion", 40 * 20)
    );
    Consumable FRIED_DRAGON_EGG = buildConsumable(
            new MobEffectInstance(MobEffects.STRENGTH, 300 * 20, 1),
            new MobEffectInstance(MobEffects.RESISTANCE, 300 * 20, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1)
    );
    Consumable DRAGON_EGG_CUSTARD_ITEM = buildConsumable(
            new MobEffectInstance(MobEffects.STRENGTH, 300 * 20, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1),
            createEndEffect("void_erosion", 35 * 20)
    );
    Consumable DRAGON_EGG_CUSTARD_BLOCK = buildConsumable(
            new MobEffectInstance(MobEffects.STRENGTH, 300 * 20, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 300 * 20, 1),
            createEndEffect("void_erosion", 40 * 20)
    );
    Consumable CHORUS_FLOWER_TEA = buildConsumable(createCookeryEffect("preservation", 180 * 20));
    Consumable CHORUS_FLOWER_CAKE = buildConsumable(createCookeryEffect("preservation", 120 * 20));
    Consumable CHORUS_SEED_COOKIE = buildConsumable(0.5F, createEndEffect("dream", 30 * 20));
    Consumable DRAGON_EGG_LIQUID_FOOD = buildConsumable(0.3F, new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 0));
    Consumable CHORUS_SEED_FOOD = buildConsumable();
    Consumable STUFFED_VOID_CONCH = buildConsumable(createEndEffect("void_erosion", 30 * 20));
    Consumable OPTIC_NERVE_SWEET_AND_SOUR_PORK_ITEM = buildConsumable(new MobEffectInstance(MobEffects.NIGHT_VISION, 480 * 20, 0));
    Consumable OPTIC_NERVE_SWEET_AND_SOUR_PORK_BLOCK = buildConsumable(new MobEffectInstance(MobEffects.NIGHT_VISION, 480 * 20, 0));
    Consumable VOID_MUTTON_STEAK_ITEM = buildConsumable(createEndEffect("void_erosion", 180 * 20));
    Consumable VOID_MUTTON_STEAK_BLOCK = buildConsumable(createEndEffect("void_erosion", 30 * 20));

    private static Consumable buildConsumable(MobEffectInstance... effects) {
        return buildConsumable(1.0F, effects);
    }

    private static Consumable buildConsumable(float probability, MobEffectInstance... effects) {
        var builder = Consumables.defaultFood();
        List<MobEffectInstance> instances = Arrays.stream(effects)
                .filter(Objects::nonNull)
                .toList();
        if (!instances.isEmpty()) {
            builder.onConsume(new ApplyStatusEffectsConsumeEffect(instances, probability));
        }
        return builder.build();
    }

    @Nullable
    private static MobEffectInstance createCookeryEffect(String path, int duration) {
        return createEffect("kaleidoscope_cookery", path, duration, 0);
    }

    @Nullable
    private static MobEffectInstance createEndEffect(String path, int duration) {
        return createEffect("kaleidoscope_end", path, duration, 0);
    }

    @Nullable
    private static MobEffectInstance createVanillaEffect(String path, int duration) {
        return createEffect("minecraft", path, duration, 0);
    }

    @Nullable
    private static MobEffectInstance createEffect(String namespace, String path, int duration, int amplifier) {
        Identifier id = Identifier.fromNamespaceAndPath(namespace, path);
        return BuiltInRegistries.MOB_EFFECT.get(id)
                .map(effect -> new MobEffectInstance(effect, duration, amplifier))
                .orElse(null);
    }

    static void init() {
    }
}

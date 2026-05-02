package com.bmt.kaleidoscope_end.datagen.subAdvancements;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.init.KEEffects;
import com.bmt.kaleidoscope_end.init.KEFoodBiteRegistry;
import com.bmt.kaleidoscope_end.init.KEItem;
import com.bmt.kaleidoscope_end.init.KETags;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@SuppressWarnings("all")
public class KEAdvances {


    public void generate(HolderLookup.@NotNull Provider provider, @NotNull Consumer<AdvancementHolder> consumer) {
        RegistryLookup<Item> itemLookup = provider.lookupOrThrow(Registries.ITEM);
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        KEItem.ENDER_MINT,
                        Component.translatable("advancements.kaleidoscope_end.root.title"),
                        Component.translatable("advancements.kaleidoscope_end.root.description"),
                        Identifier.fromNamespaceAndPath(KaleidoscopeEnd.MOD_ID, "textures/advancement/background.png"),
                        AdvancementType.TASK,
                        true, true, false
                )
                .addCriterion("mod_items", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(itemLookup, KETags.Items.MOD_ITEMS).build()
                ))
                .save(consumer, KaleidoscopeEnd.MOD_ID + ":root");

        AdvancementHolder dragonDust = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        KEItem.DRAGON_DUST,
                        Component.translatable("advancements.kaleidoscope_end.dragon_dust.title"),
                        Component.translatable("advancements.kaleidoscope_end.dragon_dust.description"),
                        null,
                        AdvancementType.TASK,
                        true, true, false
                )
                .addCriterion("dragon_dust", InventoryChangeTrigger.TriggerInstance.hasItems(
                        KEItem.DRAGON_DUST
                ))
                .save(consumer, KaleidoscopeEnd.MOD_ID + ":dragon_dust");

        AdvancementHolder dragonEggShell = Advancement.Builder.advancement()
                .parent(dragonDust)
                .display(
                        KEItem.DRAGON_EGG_SHELL,
                        Component.translatable("advancements.kaleidoscope_end.dragon_egg_shell.title"),
                        Component.translatable("advancements.kaleidoscope_end.dragon_egg_shell.description"),
                        null,
                        AdvancementType.GOAL,
                        true, true, false
                )
                .addCriterion("dragon_egg_shell", InventoryChangeTrigger.TriggerInstance.hasItems(
                        KEItem.DRAGON_EGG_SHELL
                ))
                .save(consumer, KaleidoscopeEnd.MOD_ID + ":dragon_egg_shell");

        Item darkDragonEggStewItem = BuiltInRegistries.ITEM.getValue(KEFoodBiteRegistry.DARK_DRAGON_EGG_STEW);
        Item dragonEggCustardItem = BuiltInRegistries.ITEM.getValue(KEFoodBiteRegistry.DRAGON_EGG_CUSTARD);
        
        Advancement.Builder.advancement()
                .parent(dragonEggShell)
                .display(
                        darkDragonEggStewItem,
                        Component.translatable("advancements.kaleidoscope_end.dragon_egg_diet.title"),
                        Component.translatable("advancements.kaleidoscope_end.dragon_egg_diet.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true, true, false
                )
                .addCriterion("dark_dragon_egg_stew", ConsumeItemTrigger.TriggerInstance.usedItem(
                        ItemPredicate.Builder.item().of(itemLookup, darkDragonEggStewItem)
                ))
                .addCriterion("dragon_egg_custard", ConsumeItemTrigger.TriggerInstance.usedItem(
                        ItemPredicate.Builder.item().of(itemLookup, dragonEggCustardItem)
                ))
                .requirements(AdvancementRequirements.Strategy.AND)
                .save(consumer, KaleidoscopeEnd.MOD_ID + ":dragon_egg_diet");

        Advancement.Builder.advancement()
                .parent(dragonDust)
                .display(
                        KEItem.DRAGON_TOOTH_KNIFE,
                        Component.translatable("advancements.kaleidoscope_end.dragon_tooth_knife.title"),
                        Component.translatable("advancements.kaleidoscope_end.dragon_tooth_knife.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true, true, true
                )
                .addCriterion("dragon_tooth_knife", InventoryChangeTrigger.TriggerInstance.hasItems(
                        KEItem.DRAGON_TOOTH_KNIFE
                ))
                .save(consumer, KaleidoscopeEnd.MOD_ID + ":dragon_tooth_knife");

        AdvancementHolder voidConch = Advancement.Builder.advancement()
                .parent(dragonDust)
                .display(
                        KEItem.VOID_CONCH,
                        Component.translatable("advancements.kaleidoscope_end.void_conch.title"),
                        Component.translatable("advancements.kaleidoscope_end.void_conch.description"),
                        null,
                        AdvancementType.TASK,
                        true, true, false
                )
                .addCriterion("void_conch", InventoryChangeTrigger.TriggerInstance.hasItems(
                        KEItem.VOID_CONCH
                ))
                .save(consumer, KaleidoscopeEnd.MOD_ID + ":void_conch");

        Advancement.Builder.advancement()
                .parent(voidConch)
                .display(
                        KEItem.VOID_CONCH_NOODLE_SOUP_ITEM,
                        Component.translatable("advancements.kaleidoscope_end.void_erosion.title"),
                        Component.translatable("advancements.kaleidoscope_end.void_erosion.description"),
                        null,
                        AdvancementType.GOAL,
                        true, true, false
                )
                .addCriterion("void_erosion", EffectsChangedTrigger.TriggerInstance.hasEffects(
                        MobEffectsPredicate.Builder.effects().and(KEEffects.VOID_EROSION)
                ))
                .save(consumer, KaleidoscopeEnd.MOD_ID + ":void_erosion");

        AdvancementHolder dreamBerry = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        KEItem.DREAM_BERRY_ITEM,
                        Component.translatable("advancements.kaleidoscope_end.dream_berry.title"),
                        Component.translatable("advancements.kaleidoscope_end.dream_berry.description"),
                        null,
                        AdvancementType.TASK,
                        true, true, false
                )
                .addCriterion("dream_berry", InventoryChangeTrigger.TriggerInstance.hasItems(
                        KEItem.DREAM_BERRY_ITEM
                ))
                .save(consumer, KaleidoscopeEnd.MOD_ID + ":dream_berry");

        Advancement.Builder.advancement()
                .parent(dreamBerry)
                .display(
                        KEItem.CHORUS_PETAL,
                        Component.translatable("advancements.kaleidoscope_end.dream.title"),
                        Component.translatable("advancements.kaleidoscope_end.dream.description"),
                        null,
                        AdvancementType.TASK,
                        true, true, false
                )
                .addCriterion("dream", EffectsChangedTrigger.TriggerInstance.hasEffects(
                        MobEffectsPredicate.Builder.effects().and(KEEffects.DREAM)
                ))
                .save(consumer, KaleidoscopeEnd.MOD_ID + ":dream");

        Advancement.Builder.advancement()
                .parent(root)
                .display(
                        KEItem.END_CATERPILLAR_ITEM,
                        Component.translatable("advancements.kaleidoscope_end.end_caterpillar.title"),
                        Component.translatable("advancements.kaleidoscope_end.end_caterpillar.description"),
                        null,
                        AdvancementType.TASK,
                        true, true, false
                )
                .addCriterion("end_caterpillar", InventoryChangeTrigger.TriggerInstance.hasItems(
                        KEItem.END_CATERPILLAR_ITEM
                ))
                .save(consumer, KaleidoscopeEnd.MOD_ID + ":end_caterpillar");
    }
}

package com.bmt.kaleidoscope_end.datagen.subLootTables;

import java.util.function.BiConsumer;

import net.minecraft.core.registries.BuiltInRegistries;
import org.jetbrains.annotations.NotNull;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.init.KEBlocks;
import com.bmt.kaleidoscope_end.init.KEItem;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetEnchantmentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class KEArchaeologyLoot implements LootTableSubProvider {
    private final HolderGetter<Enchantment> enchantments;

    public KEArchaeologyLoot(HolderLookup.Provider provider) {
        this.enchantments = provider.lookupOrThrow(Registries.ENCHANTMENT);
    }

    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builderBiConsumer) {
        this.addArchaeology(builderBiConsumer, KEBlocks.SUSPICIOUS_END_STONE, LootTable.lootTable().withPool(
                LootPool.lootPool().add(
                        LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                ).add(
                        LootItem.lootTableItem(KEItem.VOID_CONCH)
                ).add(
                        LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder().withEnchantment(enchantments.getOrThrow(Enchantments.SHARPNESS), UniformGenerator.between(1, 3)))
                ).add(
                        LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder().withEnchantment(enchantments.getOrThrow(Enchantments.PROTECTION), UniformGenerator.between(1, 3)))
                ).add(
                        LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder().withEnchantment(enchantments.getOrThrow(Enchantments.UNBREAKING), UniformGenerator.between(1, 3)))
                ).add(
                        LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))
                )
        ));
        this.addArchaeology(builderBiConsumer, KEBlocks.SUSPICIOUS_DRAGON_EGG, LootTable.lootTable().withPool(
                LootPool.lootPool().add(
                        LootItem.lootTableItem(KEItem.DRAGON_EGG_LIQUID).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                ).add(
                        LootItem.lootTableItem(KEItem.DRAGON_DUST).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                )
        ));
    }

    private void addArchaeology(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builderBiConsumer, Block holder, LootTable.Builder builder) {
        builderBiConsumer.accept(ResourceKey.create(Registries.LOOT_TABLE, KaleidoscopeEnd.id("archaeology/%s".formatted(BuiltInRegistries.BLOCK.getKey(holder)))), builder);
    }
}

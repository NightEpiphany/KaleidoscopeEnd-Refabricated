package com.bmt.kaleidoscope_end.event;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public final class KEExtraLootTableDrop {
    private static final ResourceLocation ENDERMITE = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/endermite");
    private static final ResourceLocation ENDERMAN = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/enderman");
    private static final ResourceLocation SHULKER = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/shulker");
    private static final ResourceLocation ENDER_DRAGON = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/ender_dragon");
    private static final ResourceLocation CHORUS_PLANT = ResourceLocation.fromNamespaceAndPath("minecraft", "blocks/chorus_plant");

    private KEExtraLootTableDrop() {
    }

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            ResourceLocation id = key.location();
            if (id.equals(ENDERMITE)) {
                addReference(tableBuilder, "endermite");
            } else if (id.equals(ENDERMAN)) {
                addReference(tableBuilder, "enderman");
            } else if (id.equals(SHULKER)) {
                addReference(tableBuilder, "shulker");
            } else if (id.equals(ENDER_DRAGON)) {
                addReference(tableBuilder, "ender_dragon");
            } else if (id.equals(CHORUS_PLANT)) {
                addReference(tableBuilder, "blocks/chorus_plant");
            }
        });
    }

    private static void addReference(LootTable.Builder tableBuilder, String path) {
        ResourceKey<LootTable> tableKey = ResourceKey.create(Registries.LOOT_TABLE, KaleidoscopeEnd.id(path));
        tableBuilder.withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(NestedLootTable.lootTableReference(tableKey)));
    }
}

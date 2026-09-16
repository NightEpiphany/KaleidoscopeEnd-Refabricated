package com.bmt.kaleidoscope_end.event;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public final class KEExtraLootTableDrop {
    private static final Identifier ENDERMITE = Identifier.fromNamespaceAndPath("minecraft", "entities/endermite");
    private static final Identifier ENDERMAN = Identifier.fromNamespaceAndPath("minecraft", "entities/enderman");
    private static final Identifier SHULKER = Identifier.fromNamespaceAndPath("minecraft", "entities/shulker");
    private static final Identifier ENDER_DRAGON = Identifier.fromNamespaceAndPath("minecraft", "entities/ender_dragon");
    private static final Identifier CHORUS_PLANT = Identifier.fromNamespaceAndPath("minecraft", "blocks/chorus_plant");

    private KEExtraLootTableDrop() {
    }

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, _, registries) -> {
            Identifier id = key.identifier();
            if (id.equals(ENDERMITE)) {
                addReference(tableBuilder, registries, "endermite");
            } else if (id.equals(ENDERMAN)) {
                addReference(tableBuilder, registries, "enderman");
            } else if (id.equals(SHULKER)) {
                addReference(tableBuilder, registries, "shulker");
            } else if (id.equals(ENDER_DRAGON)) {
                addReference(tableBuilder, registries, "ender_dragon");
            } else if (id.equals(CHORUS_PLANT)) {
                addReference(tableBuilder, registries, "blocks/chorus_plant");
            }
        });
    }

    private static void addReference(LootTable.Builder tableBuilder, HolderLookup.Provider registries, String path) {
        ResourceKey<LootTable> tableKey = ResourceKey.create(Registries.LOOT_TABLE, KaleidoscopeEnd.id(path));
        tableBuilder.withPool(LootPool.lootPool()
                .setRolls(ContextIntProviders.exactly(1))
                .add(NestedLootTable.lootTableReference(registries.lookupOrThrow(Registries.LOOT_TABLE).getOrThrow(tableKey))));
    }
}

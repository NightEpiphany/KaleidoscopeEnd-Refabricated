package com.bmt.kaleidoscope_end.datagen;

import com.bmt.kaleidoscope_end.datagen.subLootTables.KEArchaeologyLoot;
import com.bmt.kaleidoscope_end.datagen.subLootTables.KEBlockLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryProvider) {
        super(packOutput, Set.of(), List.of(
                new SubProviderEntry(KEBlockLoot::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(KEArchaeologyLoot::new, LootContextParamSets.ARCHAEOLOGY)
        ), registryProvider);
    }
}

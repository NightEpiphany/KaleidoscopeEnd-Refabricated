package com.bmt.kaleidoscope_end.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;

import com.bmt.kaleidoscope_end.init.KETags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;

public class ModEnchantmentTagsProvider extends EnchantmentTagsProvider {
    public ModEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(KETags.Enchantments.KE_ENCHANTMENTS)
            .add(ModEnchantmentProvider.VOID_ASSAULT);
    }
}

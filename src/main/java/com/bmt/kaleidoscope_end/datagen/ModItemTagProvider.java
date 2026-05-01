package com.bmt.kaleidoscope_end.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.init.KEItem;
import com.bmt.kaleidoscope_end.init.KETags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture1, CompletableFuture<TagLookup<Block>> completableFuture2) {
        super(packOutput, completableFuture1, completableFuture2);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {


        tag(KETags.Items.MOD_ITEMS)
                .add(KEItem.ENDER_MINT)
                .add(KEItem.ENDER_MINT_CANDY_ITEM)
                .add(KEItem.CHORUS_PETAL)
                .add(KEItem.CHORUS_SEED)
                .add(KEItem.CHORUS_SEED_COOKIE_ITEM)
                .add(KEItem.CHORUS_PASTA_ITEM)
                .add(KEItem.CHORUS_FLOWER_CAKE_ITEM)
                .add(KEItem.CHORUS_FLOWER_TEA_ITEM)
                .add(KEItem.DREAM_BERRY_ITEM)
                .add(KEItem.DRAGON_DUST)
                .add(KEItem.DRAGON_TOOTH)
                .add(KEItem.DRAGON_TOOTH_KNIFE)
                .add(KEItem.DRAGON_EGG_SHELL)
                .add(KEItem.DRAGON_EGG_LIQUID)
                .add(KEItem.VOID_CONCH)
                .add(KEItem.END_CATERPILLAR_ITEM)
                .add(KEItem.RAW_ENDER_DRAGON_MEAT_ITEM)
                .add(KEItem.COOKED_ENDER_DRAGON_MEAT_ITEM)
                .add(KEItem.RAW_ENDERMITE_MEAT_ITEM)
                .add(KEItem.ROASTED_ENDERMITE_MEAT_ITEM)
                .add(KEItem.SHULKER_SHELL_MEAT_ITEM)
                .add(KEItem.OPTIC_NERVE_ITEM)
                .add(KEItem.DRAGON_BREATH_BUCKET_ITEM)
                .add(KEItem.ENDER_DRAGON_SMITHING_TEMPLATE);
    }
}

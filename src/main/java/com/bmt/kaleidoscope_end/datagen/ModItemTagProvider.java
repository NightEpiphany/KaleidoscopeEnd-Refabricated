package com.bmt.kaleidoscope_end.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import org.jetbrains.annotations.NotNull;

import com.bmt.kaleidoscope_end.init.KEItem;
import com.bmt.kaleidoscope_end.init.KETags;

import net.minecraft.core.HolderLookup;
public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {


        valueLookupBuilder(KETags.Items.MOD_ITEMS)
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

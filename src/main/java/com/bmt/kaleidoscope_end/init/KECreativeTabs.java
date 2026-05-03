package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;

public final class KECreativeTabs {
    private static final Identifier MAIN_ICON_ID = KaleidoscopeEnd.id("ender_mint");

    private static final ResourceKey<CreativeModeTab> COOKERY_END_MAIN_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
            KaleidoscopeEnd.id("cookery_end_main"));

    private KECreativeTabs() {
    }

    public static void registerTabs() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, COOKERY_END_MAIN_TAB,
                FabricCreativeModeTab.builder()
                        .title(Component.translatable("itemGroup.kaleidoscope_end_foods"))
                        .icon(() -> BuiltInRegistries.ITEM.getValue(MAIN_ICON_ID).getDefaultInstance())
                        .displayItems((par, output) -> {
                            output.accept(KEItem.DRAGON_TOOTH_KNIFE);
                            output.accept(KEItem.VOID_CONCH);
                            output.accept(KEItem.DRAGON_DUST);
                            output.accept(KEItem.DRAGON_TOOTH);
                            output.accept(KEItem.DRAGON_EGG_SHELL);
//                        output.accept(KEItem.ENDERMITE_EGG);
                            output.accept(KEItem.ENDER_DRAGON_SMITHING_TEMPLATE);
                            output.accept(KEItem.ENDER_MINT);
                            output.accept(KEItem.DRAGON_BREATH_CHORUS_SOUP_ITEM);
                            output.accept(KEItem.STUFFED_SHULKER_ITEM);
                            output.accept(KEItem.CHORUS_PASTA_ITEM);
                            output.accept(KEItem.RAW_ENDER_DRAGON_MEAT_ITEM);
                            output.accept(KEItem.COOKED_ENDER_DRAGON_MEAT_ITEM);
                            output.accept(KEItem.DREAM_BERRY_ITEM);
                            output.accept(KEItem.MINT_CHORUS_MOUSSE_ITEM);
                            output.accept(KEItem.OPTIC_NERVE_ITEM);

                            KEFoodBiteRegistry.getRegisteredFoodIds().stream()
                                    .map(BuiltInRegistries.ITEM::getValue)
                                    .forEach(output::accept);

                            output.accept(KEItem.SHULKER_SHELL_MEAT_ITEM);
                            output.accept(KEItem.SHULKER_SHELL_STEW_ITEM);
                            output.accept(KEItem.SHULKER_ICE_CREAM_ITEM);
                            output.accept(KEItem.VOID_CONCH_NOODLE_SOUP_ITEM);
                            output.accept(KEItem.STIR_FRIED_ENDERMITE_MEAT_ITEM);
                            output.accept(KEItem.STIR_FRIED_ENDERMITE_MEAT_RICE_BOWL_ITEM);
                            output.accept(KEItem.RAW_ENDERMITE_MEAT_ITEM);
                            output.accept(KEItem.ROASTED_ENDERMITE_MEAT_ITEM);
                            output.accept(KEItem.END_CATERPILLAR_ITEM);
                            output.accept(KEItem.DRAGON_BREATH_MIXED_STEW_ITEM);
                            //output.accept(KEItem.DRAGON_HEAD_WITH_SAUCE_ITEM);
                            output.accept(KEItem.MINT_NOODLE_SOUP_ITEM);
                            output.accept(KEItem.MINT_SAUCE_SHULKER_MEAT_ITEM);
                            output.accept(KEItem.MINT_SAUCE_SHULKER_MEAT_RICE_BOWL_ITEM);
                            output.accept(KEItem.ENDER_MINT_CANDY_ITEM);
                            output.accept(KEItem.DRAGON_SOUFFLE_ITEM);
                            //output.accept(KEItem.DARK_DRAGON_STEAK_ITEM);
                            output.accept(KEItem.DRAGON_EGG_LIQUID);
                            output.accept(KEItem.CHORUS_SEED);
                            output.accept(KEItem.CHORUS_PETAL);
                            output.accept(KEItem.FRIED_DRAGON_EGG_ITEM);
                            //output.accept(KEItem.DRAGON_EGG_CUSTARD_ITEM);
                            output.accept(KEItem.CHORUS_FLOWER_TEA_ITEM);
                            output.accept(KEItem.CHORUS_FLOWER_CAKE_ITEM);
                            output.accept(KEItem.CHORUS_SEED_COOKIE_ITEM);

                            output.accept(KEItem.STUFFED_VOID_CONCH_ITEM);
//                        output.accept(KEItem.OPTIC_NERVE_SWEET_AND_SOUR_PORK_ITEM);

                            //output.accept(KEItem.VOID_MUTTON_STEAK_ITEM);
                            output.accept(KEItem.DRAGON_BREATH_BUCKET_ITEM);
                            output.accept(KEItem.END_STOVE_ITEM);
                        }).build());
    }
}

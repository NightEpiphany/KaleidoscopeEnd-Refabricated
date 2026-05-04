package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.item.DragonBreathBucket;
import com.bmt.kaleidoscope_end.item.DragonDustItem;
import com.bmt.kaleidoscope_end.item.DragonEggShellFoodItem;
import com.bmt.kaleidoscope_end.item.DragonToothKnifeItem;
import com.bmt.kaleidoscope_end.item.KEBlockItem;
import com.bmt.kaleidoscope_end.item.ShulkerShellFoodItem;
import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodOnlyItem;
import com.github.ysbbbbbb.kaleidoscopecookery.item.FoodWithEffectsItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public final class KEItem {
    public static DragonToothKnifeItem DRAGON_TOOTH_KNIFE;
    public static Item ENDER_MINT;
    public static Item VOID_CONCH;
    public static Item DRAGON_DUST;
    public static Item DRAGON_TOOTH;
    public static BlockItem END_STOVE_ITEM;
    public static BlockItem SUSPICIOUS_END_STONE_ITEM;
    public static BlockItem SUSPICIOUS_DRAGON_EGG_ITEM;
    public static Item ENDER_DRAGON_SMITHING_TEMPLATE;
    public static Item DRAGON_EGG_SHELL;
    public static BowlFoodOnlyItem DRAGON_BREATH_CHORUS_SOUP_ITEM;
    public static ShulkerShellFoodItem STUFFED_SHULKER_ITEM;
    public static BowlFoodOnlyItem CHORUS_PASTA_ITEM;
    public static Item RAW_ENDER_DRAGON_MEAT_ITEM;
    public static Item COOKED_ENDER_DRAGON_MEAT_ITEM;
    public static Item DREAM_BERRY_ITEM;
    public static FoodWithEffectsItem MINT_CHORUS_MOUSSE_ITEM;
    public static Item OPTIC_NERVE_ITEM;
    public static Item SHULKER_SHELL_MEAT_ITEM;
    public static BowlFoodOnlyItem SHULKER_SHELL_STEW_ITEM;
    public static ShulkerShellFoodItem SHULKER_ICE_CREAM_ITEM;
    public static BowlFoodOnlyItem VOID_CONCH_NOODLE_SOUP_ITEM;
    public static BowlFoodOnlyItem STIR_FRIED_ENDERMITE_MEAT_ITEM;
    public static BowlFoodOnlyItem STIR_FRIED_ENDERMITE_MEAT_RICE_BOWL_ITEM;
    public static Item RAW_ENDERMITE_MEAT_ITEM;
    public static Item ROASTED_ENDERMITE_MEAT_ITEM;
    public static Item END_CATERPILLAR_ITEM;
    public static ShulkerShellFoodItem DRAGON_BREATH_MIXED_STEW_ITEM;
    public static BowlFoodOnlyItem MINT_NOODLE_SOUP_ITEM;
    public static BowlFoodOnlyItem MINT_SAUCE_SHULKER_MEAT_ITEM;
    public static BowlFoodOnlyItem MINT_SAUCE_SHULKER_MEAT_RICE_BOWL_ITEM;
    public static FoodWithEffectsItem ENDER_MINT_CANDY_ITEM;
    public static BowlFoodOnlyItem DRAGON_SOUFFLE_ITEM;
    public static Item DRAGON_EGG_LIQUID;
    public static Item CHORUS_SEED;
    public static Item CHORUS_PETAL;
    public static FoodWithEffectsItem FRIED_DRAGON_EGG_ITEM;
    public static BowlFoodOnlyItem CHORUS_FLOWER_TEA_ITEM;
    public static FoodWithEffectsItem CHORUS_FLOWER_CAKE_ITEM;
    public static FoodWithEffectsItem CHORUS_SEED_COOKIE_ITEM;
    public static FoodWithEffectsItem STUFFED_VOID_CONCH_ITEM;
    public static DragonBreathBucket DRAGON_BREATH_BUCKET_ITEM;

    private KEItem() {
    }

    public static synchronized void registerItems() {
        DRAGON_TOOTH_KNIFE = new DragonToothKnifeItem(properties("dragon_tooth_knife"));
        ENDER_MINT = new KEBlockItem(KEBlocks.ENDER_MINT, properties("ender_mint"));
        VOID_CONCH = new Item(properties("void_conch"));
        DRAGON_DUST = new DragonDustItem(properties("dragon_dust"));
        DRAGON_TOOTH = new Item(properties("dragon_tooth"));
        END_STOVE_ITEM = new BlockItem(KEBlocks.END_STOVE, blockProperties("end_stove"));
        SUSPICIOUS_END_STONE_ITEM = new BlockItem(KEBlocks.SUSPICIOUS_END_STONE, blockProperties("suspicious_end_stone"));
        SUSPICIOUS_DRAGON_EGG_ITEM = new BlockItem(KEBlocks.SUSPICIOUS_DRAGON_EGG, blockProperties("suspicious_dragon_egg").rarity(Rarity.EPIC));
        ENDER_DRAGON_SMITHING_TEMPLATE = new Item(properties("ender_dragon_smithing_template"));
        DRAGON_EGG_SHELL = new Item(properties("dragon_egg_shell"));
        DRAGON_BREATH_CHORUS_SOUP_ITEM = new BowlFoodOnlyItem(properties("dragon_breath_chorus_soup"), KEFoods.DRAGON_BREATH_CHORUS_SOUP, KEConsumables.DRAGON_BREATH_CHORUS_SOUP);
        STUFFED_SHULKER_ITEM = new ShulkerShellFoodItem(properties("stuffed_shulker"), KEFoods.STUFFED_SHULKER, KEConsumables.STUFFED_SHULKER);
        CHORUS_PASTA_ITEM = new BowlFoodOnlyItem(properties("chorus_pasta"), KEFoods.CHORUS_PASTA, KEConsumables.CHORUS_PASTA);
        RAW_ENDER_DRAGON_MEAT_ITEM = new Item(properties("raw_ender_dragon_meat").food(KEFoods.RAW_ENDER_DRAGON_MEAT, KEConsumables.RAW_ENDER_DRAGON_MEAT));
        COOKED_ENDER_DRAGON_MEAT_ITEM = new Item(properties("cooked_ender_dragon_meat").food(KEFoods.COOKED_ENDER_DRAGON_MEAT, KEConsumables.COOKED_ENDER_DRAGON_MEAT));
        DREAM_BERRY_ITEM = new KEBlockItem(KEBlocks.DREAM_BERRY_HEAD, blockProperties("dream_berry").food(KEFoods.DREAM_BERRY, KEConsumables.DREAM_BERRY));
        MINT_CHORUS_MOUSSE_ITEM = new FoodWithEffectsItem(properties("mint_chorus_mousse"), KEFoods.MINT_CHORUS_MOUSSE, KEConsumables.MINT_CHORUS_MOUSSE);
        OPTIC_NERVE_ITEM = new Item(properties("optic_nerve").food(KEFoods.OPTIC_NERVE, KEConsumables.OPTIC_NERVE));
        SHULKER_SHELL_MEAT_ITEM = new Item(properties("shulker_shell_meat").food(KEFoods.SHULKER_SHELL_MEAT, KEConsumables.SHULKER_SHELL_MEAT));
        SHULKER_SHELL_STEW_ITEM = new BowlFoodOnlyItem(properties("shulker_shell_stew"), KEFoods.SHULKER_SHELL_STEW, KEConsumables.SHULKER_SHELL_STEW);
        SHULKER_ICE_CREAM_ITEM = new ShulkerShellFoodItem(properties("shulker_ice_cream"), KEFoods.SHULKER_ICE_CREAM, KEConsumables.SHULKER_ICE_CREAM);
        VOID_CONCH_NOODLE_SOUP_ITEM = new BowlFoodOnlyItem(properties("void_conch_noodle_soup"), KEFoods.VOID_CONCH_NOODLE_SOUP, KEConsumables.VOID_CONCH_NOODLE_SOUP);
        STIR_FRIED_ENDERMITE_MEAT_ITEM = new BowlFoodOnlyItem(properties("stir_fried_endermite_meat"), KEFoods.STIR_FRIED_ENDERMITE_MEAT, KEConsumables.STIR_FRIED_ENDERMITE_MEAT);
        STIR_FRIED_ENDERMITE_MEAT_RICE_BOWL_ITEM = new BowlFoodOnlyItem(properties("stir_fried_endermite_meat_rice_bowl"), KEFoods.STIR_FRIED_ENDERMITE_MEAT_RICE_BOWL, KEConsumables.STIR_FRIED_ENDERMITE_MEAT_RICE_BOWL);
        RAW_ENDERMITE_MEAT_ITEM = new Item(properties("raw_endermite_meat").food(KEFoods.RAW_ENDERMITE_MEAT, KEConsumables.RAW_ENDERMITE_MEAT));
        ROASTED_ENDERMITE_MEAT_ITEM = new Item(properties("roasted_endermite_meat").food(KEFoods.ROASTED_ENDERMITE_MEAT, KEConsumables.ROASTED_ENDERMITE_MEAT));
        END_CATERPILLAR_ITEM = new Item(properties("end_caterpillar").food(KEFoods.END_CATERPILLAR, KEConsumables.END_CATERPILLAR));
        DRAGON_BREATH_MIXED_STEW_ITEM = new ShulkerShellFoodItem(properties("dragon_breath_mixed_stew"), KEFoods.DRAGON_BREATH_MIXED_STEW, KEConsumables.DRAGON_BREATH_MIXED_STEW);
        MINT_NOODLE_SOUP_ITEM = new BowlFoodOnlyItem(properties("mint_noodle_soup"), KEFoods.MINT_NOODLE_SOUP, KEConsumables.MINT_NOODLE_SOUP);
        MINT_SAUCE_SHULKER_MEAT_ITEM = new BowlFoodOnlyItem(properties("mint_sauce_shulker_meat"), KEFoods.MINT_SAUCE_SHULKER_MEAT, KEConsumables.MINT_SAUCE_SHULKER_MEAT);
        MINT_SAUCE_SHULKER_MEAT_RICE_BOWL_ITEM = new BowlFoodOnlyItem(properties("mint_sauce_shulker_meat_rice_bowl"), KEFoods.MINT_SAUCE_SHULKER_MEAT_RICE_BOWL, KEConsumables.MINT_SAUCE_SHULKER_MEAT_RICE_BOWL);
        ENDER_MINT_CANDY_ITEM = new FoodWithEffectsItem(properties("ender_mint_candy"), KEFoods.ENDER_MINT_CANDY, KEConsumables.ENDER_MINT_CANDY);
        DRAGON_SOUFFLE_ITEM = new BowlFoodOnlyItem(properties("dragon_souffle"), KEFoods.DRAGON_SOUFFLE, KEConsumables.DRAGON_SOUFFLE);
        DRAGON_EGG_LIQUID = new Item(properties("dragon_egg_liquid").food(KEFoods.DRAGON_EGG_LIQUID_FOOD, KEConsumables.DRAGON_EGG_LIQUID_FOOD));
        CHORUS_SEED = new Item(properties("chorus_seed").food(KEFoods.CHORUS_SEED_FOOD, KEConsumables.CHORUS_SEED_FOOD));
        CHORUS_PETAL = new Item(properties("chorus_petal"));
        FRIED_DRAGON_EGG_ITEM = new FoodWithEffectsItem(properties("fried_dragon_egg"), KEFoods.FRIED_DRAGON_EGG, KEConsumables.FRIED_DRAGON_EGG);
        CHORUS_FLOWER_TEA_ITEM = new BowlFoodOnlyItem(properties("chorus_flower_tea"), KEFoods.CHORUS_FLOWER_TEA, KEConsumables.CHORUS_FLOWER_TEA);
        CHORUS_FLOWER_CAKE_ITEM = new FoodWithEffectsItem(properties("chorus_flower_cake"), KEFoods.CHORUS_FLOWER_CAKE, KEConsumables.CHORUS_FLOWER_CAKE);
        CHORUS_SEED_COOKIE_ITEM = new FoodWithEffectsItem(properties("chorus_seed_cookie"), KEFoods.CHORUS_SEED_COOKIE, KEConsumables.CHORUS_SEED_COOKIE);
        STUFFED_VOID_CONCH_ITEM = new FoodWithEffectsItem(properties("stuffed_void_conch"), KEFoods.STUFFED_VOID_CONCH, KEConsumables.STUFFED_VOID_CONCH);
        DRAGON_BREATH_BUCKET_ITEM = new DragonBreathBucket(properties("dragon_breath_bucket"));

        register("dragon_tooth_knife", DRAGON_TOOTH_KNIFE);
        register("ender_mint", ENDER_MINT);
        register("void_conch", VOID_CONCH);
        register("dragon_dust", DRAGON_DUST);
        register("dragon_tooth", DRAGON_TOOTH);
        register("end_stove", END_STOVE_ITEM);
        register("suspicious_end_stone", SUSPICIOUS_END_STONE_ITEM);
        register("suspicious_dragon_egg", SUSPICIOUS_DRAGON_EGG_ITEM);
        register("ender_dragon_smithing_template", ENDER_DRAGON_SMITHING_TEMPLATE);
        register("dragon_egg_shell", DRAGON_EGG_SHELL);
        register("dragon_breath_chorus_soup", DRAGON_BREATH_CHORUS_SOUP_ITEM);
        register("stuffed_shulker", STUFFED_SHULKER_ITEM);
        register("chorus_pasta", CHORUS_PASTA_ITEM);
        register("raw_ender_dragon_meat", RAW_ENDER_DRAGON_MEAT_ITEM);
        register("cooked_ender_dragon_meat", COOKED_ENDER_DRAGON_MEAT_ITEM);
        register("dream_berry", DREAM_BERRY_ITEM);
        register("mint_chorus_mousse", MINT_CHORUS_MOUSSE_ITEM);
        register("optic_nerve", OPTIC_NERVE_ITEM);
        register("shulker_shell_meat", SHULKER_SHELL_MEAT_ITEM);
        register("shulker_shell_stew", SHULKER_SHELL_STEW_ITEM);
        register("shulker_ice_cream", SHULKER_ICE_CREAM_ITEM);
        register("void_conch_noodle_soup", VOID_CONCH_NOODLE_SOUP_ITEM);
        register("stir_fried_endermite_meat", STIR_FRIED_ENDERMITE_MEAT_ITEM);
        register("stir_fried_endermite_meat_rice_bowl", STIR_FRIED_ENDERMITE_MEAT_RICE_BOWL_ITEM);
        register("raw_endermite_meat", RAW_ENDERMITE_MEAT_ITEM);
        register("roasted_endermite_meat", ROASTED_ENDERMITE_MEAT_ITEM);
        register("end_caterpillar", END_CATERPILLAR_ITEM);
        register("dragon_breath_mixed_stew", DRAGON_BREATH_MIXED_STEW_ITEM);
        register("mint_noodle_soup", MINT_NOODLE_SOUP_ITEM);
        register("mint_sauce_shulker_meat", MINT_SAUCE_SHULKER_MEAT_ITEM);
        register("mint_sauce_shulker_meat_rice_bowl", MINT_SAUCE_SHULKER_MEAT_RICE_BOWL_ITEM);
        register("ender_mint_candy", ENDER_MINT_CANDY_ITEM);
        register("dragon_souffle", DRAGON_SOUFFLE_ITEM);
        register("dragon_egg_liquid", DRAGON_EGG_LIQUID);
        register("chorus_seed", CHORUS_SEED);
        register("chorus_petal", CHORUS_PETAL);
        register("fried_dragon_egg", FRIED_DRAGON_EGG_ITEM);
        register("chorus_flower_tea", CHORUS_FLOWER_TEA_ITEM);
        register("chorus_flower_cake", CHORUS_FLOWER_CAKE_ITEM);
        register("chorus_seed_cookie", CHORUS_SEED_COOKIE_ITEM);
        register("stuffed_void_conch", STUFFED_VOID_CONCH_ITEM);
        register("dragon_breath_bucket", DRAGON_BREATH_BUCKET_ITEM);
    }

    private static void register(String path, Item item) {
        Registry.register(BuiltInRegistries.ITEM, KaleidoscopeEnd.id(path), item);
    }

    private static Item.Properties properties(String path) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, KaleidoscopeEnd.id(path)));
    }

    private static Item.Properties blockProperties(String path) {
        return properties(path).useBlockDescriptionPrefix();
    }
}

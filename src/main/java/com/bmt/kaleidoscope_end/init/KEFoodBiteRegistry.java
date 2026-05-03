package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteOneByTwoBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteRegistry;
import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodBlockItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class KEFoodBiteRegistry {
    public static ResourceLocation END_SALAD;
    public static ResourceLocation DARK_DRAGON_STEAK;
    public static ResourceLocation OPTIC_NERVE_SWEET_AND_SOUR_PORK;
    public static ResourceLocation VOID_MUTTON_STEAK;
    public static ResourceLocation DRAGON_HEAD_WITH_SAUCE;
    public static ResourceLocation DARK_DRAGON_EGG_STEW;
    public static ResourceLocation DRAGON_EGG_CUSTARD;
    public static ResourceLocation END_CATERPILLAR_SASHIMI;
    public static ResourceLocation DRAGON_EGG_ICE_CREAM;
    private static final Map<ResourceLocation, FoodBiteRegistry.FoodData> FOOD_DATA_MAP = new LinkedHashMap<>();

    private KEFoodBiteRegistry() {
    }

    public static void init() {
        FOOD_DATA_MAP.clear();

        // 龙蛋冰淇淋
                DRAGON_EGG_ICE_CREAM = registerFoodData(KaleidoscopeEnd.id("dragon_egg_ice_cream"), FoodBiteRegistry.FoodData
                .create(3, KEFoods.DRAGON_EGG_ICE_CREAM_BLOCK, KEFoods.DRAGON_EGG_ICE_CREAM_ITEM)
                .setLootItem(KEItem.DRAGON_EGG_SHELL)
                .setAABB(Block.box(1, 0, 1, 15, 11, 15)));

        // 末地猪儿虫刺身
        END_CATERPILLAR_SASHIMI = registerFoodData(KaleidoscopeEnd.id("end_caterpillar_sashimi"), FoodBiteRegistry.FoodData
                .create(3, KEFoods.END_CATERPILLAR_SASHIMI_BLOCK, KEFoods.END_CATERPILLAR_SASHIMI_ITEM));


        // 末地沙拉
        END_SALAD = registerFoodData(KaleidoscopeEnd.id("end_salad"), FoodBiteRegistry.FoodData
                .create(3, KEFoods.END_SALAD_BLOCK, KEFoods.END_SALAD_ITEM));

        // 暗黑龙排
        DARK_DRAGON_STEAK = registerFoodData(KaleidoscopeEnd.id("dark_dragon_steak"), FoodBiteRegistry.FoodData
                .create(4, KEFoods.DARK_DRAGON_STEAK_BLOCK, KEFoods.DARK_DRAGON_STEAK_ITEM));

        // 视神经咕噜肉
        OPTIC_NERVE_SWEET_AND_SOUR_PORK = registerFoodData(KaleidoscopeEnd.id("optic_nerve_sweet_and_sour_pork"), FoodBiteRegistry.FoodData
                .create(3, KEFoods.OPTIC_NERVE_SWEET_AND_SOUR_PORK_BLOCK, KEFoods.OPTIC_NERVE_SWEET_AND_SOUR_PORK_ITEM));

        // 虚空羊排
        VOID_MUTTON_STEAK = registerFoodData(KaleidoscopeEnd.id("void_mutton_steak"), FoodBiteRegistry.FoodData
                .create(3, KEFoods.VOID_MUTTON_STEAK_BLOCK, KEFoods.VOID_MUTTON_STEAK_ITEM));

        // 浇汁龙首
        DRAGON_HEAD_WITH_SAUCE = registerFoodData(KaleidoscopeEnd.id("dragon_head_with_sauce"), FoodBiteRegistry.FoodData
                .createOneByTwo(6, KEFoods.DRAGON_HEAD_WITH_SAUCE_BLOCK, KEFoods.DRAGON_HEAD_WITH_SAUCE_ITEM));

        // 龙蛋煲
        DARK_DRAGON_EGG_STEW = registerFoodData(KaleidoscopeEnd.id("dark_dragon_egg_stew"), FoodBiteRegistry.FoodData
                .create(4, KEFoods.DARK_DRAGON_EGG_STEW_BLOCK, KEFoods.DARK_DRAGON_EGG_STEW_ITEM)
                .setLootItem(KEItem.DRAGON_EGG_SHELL)
                .setAABB(Block.box(1, 0, 1, 15, 11, 15)));

        // 龙蛋羹
        DRAGON_EGG_CUSTARD = registerFoodData(KaleidoscopeEnd.id("dragon_egg_custard"), FoodBiteRegistry.FoodData
                .create(4, KEFoods.DRAGON_EGG_CUSTARD_BLOCK, KEFoods.DRAGON_EGG_CUSTARD_ITEM)
                .setLootItem(KEItem.DRAGON_EGG_SHELL)
                .setAABB(Block.box(1, 0, 1, 15, 11, 15)));

        registerFoodBlocksAndItems();
    }

    public static List<ResourceLocation> getRegisteredFoodIds() {
        return List.copyOf(FOOD_DATA_MAP.keySet());
    }

    private static ResourceLocation registerFoodData(ResourceLocation id, FoodBiteRegistry.FoodData data) {
        FOOD_DATA_MAP.put(id, data);
        return id;
    }

    private static void registerFoodBlocksAndItems() {
        FOOD_DATA_MAP.forEach((resourceLocation, data) -> {
            FoodBiteBlock biteBlock = createFoodBiteBlock(data);
            Registry.register(BuiltInRegistries.BLOCK, resourceLocation, biteBlock);

            ItemLike usingConvertsTo = data.getLootItems().getFirst();
            Registry.register(BuiltInRegistries.ITEM, resourceLocation,
                    new BowlFoodBlockItem(biteBlock, data.itemFood(), usingConvertsTo));
        });
    }

    private static FoodBiteBlock createFoodBiteBlock(FoodBiteRegistry.FoodData data) {
        FoodBiteBlock biteBlock = data.blockType() == FoodBiteRegistry.BlockType.ONE_BY_TWO
                ? new FoodBiteOneByTwoBlock(data.blockFood(), data.maxBites(), data.animateTick())
                : new FoodBiteBlock(data.blockFood(), data.maxBites(), data.animateTick());

        VoxelShape aabb = data.getAABB();
        if (aabb != null) {
            biteBlock.setAABB(aabb);
        }
        return biteBlock;
    }
}

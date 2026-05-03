package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.item.KEBowlFoodBlockItem;
import com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteOneByTwoBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class KEFoodBiteRegistry {
    public static Identifier END_SALAD;
    public static Identifier DARK_DRAGON_STEAK;
    public static Identifier OPTIC_NERVE_SWEET_AND_SOUR_PORK;
    public static Identifier VOID_MUTTON_STEAK;
    public static Identifier DRAGON_HEAD_WITH_SAUCE;
    public static Identifier DARK_DRAGON_EGG_STEW;
    public static Identifier DRAGON_EGG_CUSTARD;
    public static Identifier END_CATERPILLAR_SASHIMI;
    public static Identifier DRAGON_EGG_ICE_CREAM;
    private static final Map<Identifier, FoodBiteRegistry.FoodData> FOOD_DATA_MAP = new ConcurrentHashMap<>();
    private static final CopyOnWriteArrayList<Identifier> FOOD_DATA_ORDER = new CopyOnWriteArrayList<>();

    private KEFoodBiteRegistry() {
    }

    public static void init() {
        FOOD_DATA_MAP.clear();
        FOOD_DATA_ORDER.clear();

        // 龙蛋冰淇淋
        DRAGON_EGG_ICE_CREAM = registerFoodData(KaleidoscopeEnd.id("dragon_egg_ice_cream"), FoodBiteRegistry.FoodData
                .create(3, KEFoods.DRAGON_EGG_ICE_CREAM_BLOCK, KEFoods.DRAGON_EGG_ICE_CREAM_ITEM, KEConsumables.DRAGON_EGG_ICE_CREAM_BLOCK, KEConsumables.DRAGON_EGG_ICE_CREAM_ITEM)
                .setLootItem(KEItem.DRAGON_EGG_SHELL)
                .setAABB(Block.box(1, 0, 1, 15, 11, 15)));

        // 末地猪儿虫刺身
        END_CATERPILLAR_SASHIMI = registerFoodData(KaleidoscopeEnd.id("end_caterpillar_sashimi"), FoodBiteRegistry.FoodData
                .create(3, KEFoods.END_CATERPILLAR_SASHIMI_BLOCK, KEFoods.END_CATERPILLAR_SASHIMI_ITEM, KEConsumables.END_CATERPILLAR_SASHIMI_BLOCK, KEConsumables.END_CATERPILLAR_SASHIMI_ITEM));

        // 末地沙拉
        END_SALAD = registerFoodData(KaleidoscopeEnd.id("end_salad"), FoodBiteRegistry.FoodData
                .create(3, KEFoods.END_SALAD_BLOCK, KEFoods.END_SALAD_ITEM, KEConsumables.END_SALAD_BLOCK, KEConsumables.END_SALAD_ITEM));

        // 暗黑龙排
        DARK_DRAGON_STEAK = registerFoodData(KaleidoscopeEnd.id("dark_dragon_steak"), FoodBiteRegistry.FoodData
                .create(4, KEFoods.DARK_DRAGON_STEAK_BLOCK, KEFoods.DARK_DRAGON_STEAK_ITEM, KEConsumables.DARK_DRAGON_STEAK_BLOCK, KEConsumables.DARK_DRAGON_STEAK_ITEM));

        // 视神经咕噜肉
        OPTIC_NERVE_SWEET_AND_SOUR_PORK = registerFoodData(KaleidoscopeEnd.id("optic_nerve_sweet_and_sour_pork"), FoodBiteRegistry.FoodData
                .create(3, KEFoods.OPTIC_NERVE_SWEET_AND_SOUR_PORK_BLOCK, KEFoods.OPTIC_NERVE_SWEET_AND_SOUR_PORK_ITEM,
                        KEConsumables.OPTIC_NERVE_SWEET_AND_SOUR_PORK_BLOCK, KEConsumables.OPTIC_NERVE_SWEET_AND_SOUR_PORK_ITEM));

        // 虚空羊排
        VOID_MUTTON_STEAK = registerFoodData(KaleidoscopeEnd.id("void_mutton_steak"), FoodBiteRegistry.FoodData
                .create(3, KEFoods.VOID_MUTTON_STEAK_BLOCK, KEFoods.VOID_MUTTON_STEAK_ITEM, KEConsumables.VOID_MUTTON_STEAK_BLOCK, KEConsumables.VOID_MUTTON_STEAK_ITEM));

        // 浇汁龙首
        DRAGON_HEAD_WITH_SAUCE = registerFoodData(KaleidoscopeEnd.id("dragon_head_with_sauce"), FoodBiteRegistry.FoodData
                .createOneByTwo(6, KEFoods.DRAGON_HEAD_WITH_SAUCE_BLOCK, KEFoods.DRAGON_HEAD_WITH_SAUCE_ITEM,
                        KEConsumables.DRAGON_HEAD_WITH_SAUCE_BLOCK, KEConsumables.DRAGON_HEAD_WITH_SAUCE_ITEM));

        // 龙蛋煲
        DARK_DRAGON_EGG_STEW = registerFoodData(KaleidoscopeEnd.id("dark_dragon_egg_stew"), FoodBiteRegistry.FoodData
                .create(4, KEFoods.DARK_DRAGON_EGG_STEW_BLOCK, KEFoods.DARK_DRAGON_EGG_STEW_ITEM,
                        KEConsumables.DARK_DRAGON_EGG_STEW_BLOCK, KEConsumables.DARK_DRAGON_EGG_STEW_ITEM)
                .setLootItem(KEItem.DRAGON_EGG_SHELL)
                .setAABB(Block.box(1, 0, 1, 15, 11, 15)));

        // 龙蛋羹
        DRAGON_EGG_CUSTARD = registerFoodData(KaleidoscopeEnd.id("dragon_egg_custard"), FoodBiteRegistry.FoodData
                .create(4, KEFoods.DRAGON_EGG_CUSTARD_BLOCK, KEFoods.DRAGON_EGG_CUSTARD_ITEM,
                        KEConsumables.DRAGON_EGG_CUSTARD_BLOCK, KEConsumables.DRAGON_EGG_CUSTARD_ITEM)
                .setLootItem(KEItem.DRAGON_EGG_SHELL)
                .setAABB(Block.box(1, 0, 1, 15, 11, 15)));

        registerFoodBlocksAndItems();
    }

    public static List<Identifier> getRegisteredFoodIds() {
        return List.copyOf(FOOD_DATA_ORDER);
    }

    private static Identifier registerFoodData(Identifier id, FoodBiteRegistry.FoodData data) {
        FOOD_DATA_MAP.put(id, data);
        FOOD_DATA_ORDER.addIfAbsent(id);
        return id;
    }

    private static void registerFoodBlocksAndItems() {
        FOOD_DATA_MAP.forEach((id, data) -> {
            FoodBiteBlock biteBlock = createFoodBiteBlock(data, id.getPath());
            Registry.register(BuiltInRegistries.BLOCK, id, biteBlock);

            Block block = BuiltInRegistries.BLOCK.getValue(id);
            ItemLike usingConvertsTo = data.getLootItems().getFirst();
            Registry.register(BuiltInRegistries.ITEM, id,
                    new KEBowlFoodBlockItem(block, data.itemFood(), data.itemConsumable(), usingConvertsTo, id.getPath()));
        });
    }

    private static FoodBiteBlock createFoodBiteBlock(FoodBiteRegistry.FoodData data, String name) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                .forceSolidOn()
                .instabreak()
                .mapColor(MapColor.WOOD)
                .sound(SoundType.WOOD)
                .pushReaction(PushReaction.DESTROY)
                .noOcclusion();

        FoodBiteBlock biteBlock = data.blockType() == FoodBiteRegistry.BlockType.ONE_BY_TWO
                ? new FoodBiteOneByTwoBlock(
                properties.setId(ResourceKey.create(Registries.BLOCK, KaleidoscopeEnd.id(name))),
                data.blockFood(),
                data.blockConsumable(),
                data.maxBites(),
                data.animateTick()
        )
                : new FoodBiteBlock(
                properties.setId(ResourceKey.create(Registries.BLOCK, KaleidoscopeEnd.id(name))),
                data.blockFood(),
                data.blockConsumable(),
                data.maxBites(),
                data.animateTick()
        );

        VoxelShape aabb = data.getAABB();
        if (aabb != null) {
            biteBlock.setAABB(aabb);
        }
        return biteBlock;
    }
}

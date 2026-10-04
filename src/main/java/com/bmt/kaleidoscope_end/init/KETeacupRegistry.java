package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.block.drink.TeacupBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteAnimateTicks;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.TeacupRegistry;
import com.github.ysbbbbbb.kaleidoscopecookery.item.TeacupItem;
import com.github.ysbbbbbb.kaleidoscopecookery.util.PortHelper;
import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public final class KETeacupRegistry {
    public static final Map<Identifier, TeacupRegistry.TeacupData> TEACUP_DATA_MAP = Maps.newLinkedHashMap();
    private static final CopyOnWriteArrayList<Identifier> TEACUP_DATA_ORDER = new CopyOnWriteArrayList<>();

    public static final Identifier CHORUS_FLOWER_TEA = id("chorus_flower_tea");
    public static final Identifier ENDER_DRAGON_TEA = id("ender_dragon_tea");
    public static final Identifier VOID_TEA = id("void_tea");
    public static final Identifier ENDER_MINT_TEA = id("ender_mint_tea");
    static {
        bootstrap();
    }
    private static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(KaleidoscopeEnd.MOD_ID, name);
    }

    public static List<Identifier> getRegisteredTeaIds() {
        return List.copyOf(TEACUP_DATA_ORDER);
    }

    private static void bootstrap() {
        TEACUP_DATA_MAP.clear();
        TEACUP_DATA_ORDER.clear();

        registerTeacupData(CHORUS_FLOWER_TEA, TeacupRegistry.TeacupData.create(4)
                .addEffect(() -> new MobEffectInstance(MobEffects.GLOWING, 45 * 20))
                .setAnimateTick(FoodBiteAnimateTicks.SUSPICIOUS_STIR_FRY_ANIMATE_TICK));

        registerTeacupData(ENDER_DRAGON_TEA, TeacupRegistry.TeacupData.create(4)
                .addEffect(() -> new MobEffectInstance(KEEffects.DREAM, 15 * 20))
                .setAnimateTick(FoodBiteAnimateTicks.SUSPICIOUS_STIR_FRY_ANIMATE_TICK));

        registerTeacupData(VOID_TEA, TeacupRegistry.TeacupData.create(4)
                .addEffect(() -> new MobEffectInstance(KEEffects.VOID_EROSION, 15 * 20))
                .setAnimateTick(FoodBiteAnimateTicks.SUSPICIOUS_STIR_FRY_ANIMATE_TICK));

        registerTeacupData(ENDER_MINT_TEA, TeacupRegistry.TeacupData.create(4)
                .addEffect(() -> new MobEffectInstance(KEEffects.MINT, 15 * 20))
                .setAnimateTick(FoodBiteAnimateTicks.SUSPICIOUS_STIR_FRY_ANIMATE_TICK));

        TEACUP_DATA_MAP.forEach((resourceLocation, data) -> {
            TeacupBlock teacupBlock = new TeacupBlock(
                    BlockBehaviour.Properties
                            .of()
                            .setId(ResourceKey.create(Registries.BLOCK, KaleidoscopeEnd.id(resourceLocation.getPath()))),
                    data.getMaxCount(),
                    data.getAnimateTick());
            VoxelShape aabb = data.getAABB();
            if (aabb != null) {
                teacupBlock.setAABB(aabb);
            }
            Registry.register(BuiltInRegistries.BLOCK, resourceLocation, teacupBlock);

            Block block = BuiltInRegistries.BLOCK.getValue(resourceLocation);
            Registry.register(BuiltInRegistries.ITEM, resourceLocation, new TeacupItem(block, data.getEffects(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, KaleidoscopeEnd.id(resourceLocation.getPath())))));
        });
    }

    public static void registerTeacupData(Identifier id, TeacupRegistry.TeacupData data) {
        TEACUP_DATA_MAP.put(id, data);
        TEACUP_DATA_ORDER.addIfAbsent(id);
    }

    public static void init(){}
}

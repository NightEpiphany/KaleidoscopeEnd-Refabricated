package com.bmt.kaleidoscope_end.datagen;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantmentProvider {
    
    public static final ResourceKey<Enchantment> VOID_ASSAULT = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(KaleidoscopeEnd.MOD_ID, "void_assault")
    );

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        
        register(context, VOID_ASSAULT, 
            Enchantment.enchantment(
                Enchantment.definition(
                    items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                    items.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                    3,
                    3,
                    Enchantment.dynamicCost(15, 9),
                    Enchantment.dynamicCost(65, 9),
                    4,
                    EquipmentSlotGroup.MAINHAND
                )
            )
        );
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}

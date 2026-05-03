package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class KEEnchantments {
    public static final ResourceKey<Enchantment> VOID_ASSAULT = ResourceKey.create(
            Registries.ENCHANTMENT,
            Identifier.fromNamespaceAndPath(KaleidoscopeEnd.MOD_ID, "void_assault")
    );
    public static final ResourceKey<Enchantment> VOID_ECHO = ResourceKey.create(
            Registries.ENCHANTMENT,
            Identifier.fromNamespaceAndPath(KaleidoscopeEnd.MOD_ID, "void_echo")
    );
    public static final ResourceKey<Enchantment> VOID_WALKER = ResourceKey.create(
            Registries.ENCHANTMENT,
            Identifier.fromNamespaceAndPath(KaleidoscopeEnd.MOD_ID, "void_walker")
    );
}

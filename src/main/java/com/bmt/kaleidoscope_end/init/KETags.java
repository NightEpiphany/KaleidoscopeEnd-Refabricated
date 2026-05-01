package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public final class KETags {
    private KETags() {
    }

    public static final class Items {
        public static final TagKey<Item> MOD_ITEMS = TagKey.create(Registries.ITEM, KaleidoscopeEnd.id("mod_items"));
        public static final TagKey<Item> ENCHANTING_FUELS = TagKey.create(Registries.ITEM, KaleidoscopeEnd.id("enchanting_fuels"));

        private Items() {
        }
    }

    public static final class Enchantments {
        public static final TagKey<Enchantment> KE_ENCHANTMENTS = TagKey.create(Registries.ENCHANTMENT, KaleidoscopeEnd.id("ke_enchantments"));

        private Enchantments() {
        }
    }

    public static final class Blocks {
        public static final TagKey<Block> END_STONE_GROWABLE = TagKey.create(Registries.BLOCK, KaleidoscopeEnd.id("end_stone_growable"));

        private Blocks() {
        }
    }
}

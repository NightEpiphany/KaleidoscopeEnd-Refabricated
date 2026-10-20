package com.bmt.kaleidoscope_end.lib.api.creative;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class CreativeTabSections {
    private static final Map<Identifier, CreativeSection> SECTIONS = new LinkedHashMap<>(4);
    private static final Map<Item, Identifier> ITEM_SECTIONS = new IdentityHashMap<>(64);
    private static final Map<CreativeModeTab, CreativeTabLayout> LAYOUTS = new IdentityHashMap<>(8);

    private CreativeTabSections() {
    }

    public static synchronized void registerSection(CreativeSection section) {
        if (section == null) {
            throw new NullPointerException("section");
        }

        CreativeSection previous = SECTIONS.putIfAbsent(section.id(), section);
        if (previous != null && !previous.equals(section)) {
            throw new IllegalStateException("[CreativeTabSections] Duplicate section id: " + section.id());
        }
        LAYOUTS.clear();
    }

    public static synchronized void registerItem(Identifier sectionId, Item item) {
        if (sectionId == null || item == null) {
            throw new NullPointerException("sectionId and item are required");
        }

        Identifier previous = ITEM_SECTIONS.putIfAbsent(item, sectionId);
        if (previous != null && !previous.equals(sectionId)) {
            Identifier itemId = BuiltInRegistries.ITEM.getKey(item);
            throw new IllegalStateException(
                    "[CreativeTabSections] Item " + itemId + " is bound to both " + previous + " and " + sectionId
            );
        }
        LAYOUTS.clear();
    }

    public static void registerItem(Identifier sectionId, Supplier<? extends Item> itemSupplier) {
        if (itemSupplier == null) {
            throw new NullPointerException("itemSupplier");
        }
        registerItem(sectionId, itemSupplier.get());
    }

    public static synchronized CreativeTabLayout getLayout(CreativeModeTab tab) {
        return LAYOUTS.getOrDefault(tab, CreativeTabLayout.EMPTY);
    }

    public static synchronized PreparedCreativeTabContents prepare(
            CreativeModeTab tab,
            Collection<ItemStack> originalItems,
            Set<ItemStack> originalSearchItems
    ) {
        ArrayList<ItemStack> sourceItems = new ArrayList<>(originalItems.size());
        for (ItemStack stack : originalItems) {
            if (!stack.isEmpty()) {
                sourceItems.add(stack);
            }
        }

        Identifier tabId = BuiltInRegistries.CREATIVE_MODE_TAB.getKey(tab);
        if (tabId == null) {
            return new PreparedCreativeTabContents(sourceItems, originalSearchItems, CreativeTabLayout.EMPTY);
        }

        ArrayList<CreativeSection> sections = new ArrayList<>(SECTIONS.size());
        for (CreativeSection section : SECTIONS.values()) {
            if (section.tab().equals(tabId)) {
                sections.add(section);
            }
        }
        if (sections.isEmpty()) {
            LAYOUTS.remove(tab);
            return new PreparedCreativeTabContents(sourceItems, originalSearchItems, CreativeTabLayout.EMPTY);
        }
        sections.sort(CreativeSection::compareTo);

        Map<CreativeSection, List<ItemStack>> grouped = new LinkedHashMap<>(sections.size());
        for (CreativeSection section : sections) {
            grouped.put(section, new ArrayList<>(sourceItems.size()));
        }

        ArrayList<ItemStack> ungrouped = new ArrayList<>(sourceItems.size());
        for (ItemStack stack : sourceItems) {
            Identifier sectionId = ITEM_SECTIONS.get(stack.getItem());
            CreativeSection section = sectionId == null ? null : SECTIONS.get(sectionId);
            if (section == null || !section.tab().equals(tabId)) {
                ungrouped.add(stack);
            } else {
                grouped.get(section).add(stack);
            }
        }

        ArrayList<ItemStack> arranged = new ArrayList<>(sourceItems.size() + sections.size() * 17);
        arranged.addAll(ungrouped);
        ArrayList<CreativeTabBanner> banners = new ArrayList<>(sections.size());
        for (CreativeSection section : sections) {
            List<ItemStack> sectionItems = grouped.get(section);
            if (sectionItems.isEmpty()) {
                continue;
            }

            padToNextRow(arranged);
            banners.add(new CreativeTabBanner(section, arranged.size() / 9));
            for (int i = 0; i < 9; i++) {
                arranged.add(ItemStack.EMPTY);
            }
            arranged.addAll(sectionItems);
        }

        CreativeTabLayout layout = banners.isEmpty() ? CreativeTabLayout.EMPTY : new CreativeTabLayout(banners);
        LAYOUTS.put(tab, layout);
        return new PreparedCreativeTabContents(arranged, originalSearchItems, layout);
    }

    private static void padToNextRow(List<ItemStack> items) {
        int remainder = items.size() % 9;
        if (remainder == 0) {
            return;
        }
        for (int i = remainder; i < 9; i++) {
            items.add(ItemStack.EMPTY);
        }
    }
}

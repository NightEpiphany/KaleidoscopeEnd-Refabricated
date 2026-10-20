package com.bmt.kaleidoscope_end.lib.client;

import com.bmt.kaleidoscope_end.lib.api.creative.CreativeSection;
import com.bmt.kaleidoscope_end.lib.api.creative.CreativeTabBanner;
import com.bmt.kaleidoscope_end.lib.api.creative.CreativeTabLayout;
import com.bmt.kaleidoscope_end.lib.api.creative.CreativeTabSections;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;

import java.util.Map;
import java.util.WeakHashMap;

@Environment(EnvType.CLIENT)
public final class CreativeSectionRenderer {
    private static final Map<CreativeModeInventoryScreen.ItemPickerMenu, Integer> CURRENT_ROWS = new WeakHashMap<>(8);

    private CreativeSectionRenderer() {
    }

    public static synchronized void updateScroll(CreativeModeInventoryScreen.ItemPickerMenu menu, int row) {
        CURRENT_ROWS.put(menu, row);
    }

    public static synchronized void render(
            CreativeModeInventoryScreen screen,
            GuiGraphicsExtractor graphics,
            CreativeModeTab selectedTab,
            EditBox searchBox,
            int left,
            int top
    ) {
        if (searchBox != null && !searchBox.getValue().isEmpty()) {
            return;
        }

        CreativeTabLayout layout = CreativeTabSections.getLayout(selectedTab);
        if (layout.banners().isEmpty()) {
            return;
        }

        int currentRow = CURRENT_ROWS.getOrDefault(screen.getMenu(), 0);
        for (CreativeTabBanner banner : layout.banners()) {
            int visibleRow = banner.row() - currentRow;
            if (visibleRow >= 0 && visibleRow < 5) {
                drawBanner(graphics, banner.section(), left + 8, top + 17 + visibleRow * 18);
            }
        }
    }

    private static void drawBanner(GuiGraphicsExtractor graphics, CreativeSection section, int x, int y) {
        Identifier banner = section.banner();
        if (banner != null) {
            graphics.blit(banner, x, y, x + 162, y + 18, 0.0F, 1.0F, 0.0F, 1.0F);
        } else {
            graphics.fill(x, y, x + 162, y + 18, section.background());
        }

        Font font = Minecraft.getInstance().font;
        int textWidth = Math.min(font.width(section.title()), 150);
        graphics.fill(x + 2, y + 2, x + textWidth + 8, y + 16, section.textBackground());
        graphics.text(font, section.title(), x + 5, y + 5, withOpaqueAlpha(section.bottomColor()), true);
        if (textWidth > 0) {
            graphics.enableScissor(x + 5, y + 5, x + 5 + textWidth, y + 10);
            graphics.text(font, section.title(), x + 5, y + 5, withOpaqueAlpha(section.topColor()), false);
            graphics.disableScissor();
        }
    }

    private static int withOpaqueAlpha(int color) {
        return (color >>> 24) == 0 ? color | 0xFF000000 : color;
    }
}

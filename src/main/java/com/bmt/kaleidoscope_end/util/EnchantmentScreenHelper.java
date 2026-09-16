package com.bmt.kaleidoscope_end.util;

import com.bmt.kaleidoscope_end.init.KEItem;
import net.minecraft.world.inventory.EnchantmentMenu;

public class EnchantmentScreenHelper {

    private static final int VANILLA_ENABLED_COLOR = 8453920;
    private static final int VANILLA_DISABLED_COLOR = 6839882;
    private static final int VANILLA_DISABLED_HALF = 3419941;
    private static final int VANILLA_LEVEL_COLOR = 4226832;
    private static final int VANILLA_HIGHLIGHTED_COLOR = 16777088;

    private static final int CUSTOM_ENABLED_COLOR = 11141290;
    private static final int CUSTOM_DISABLED_COLOR = 4539717;
    private static final int CUSTOM_DISABLED_HALF = 2269858;
    private static final int CUSTOM_LEVEL_COLOR = 6684774;
    private static final int CUSTOM_HIGHLIGHTED_COLOR = 14745855;

    public static int warpColor(int color, EnchantmentMenu menu) {
        if (!menu.getSlot(1).getItem().is(KEItem.VOID_CONCH)) {
            return color;
        }

        int rgb = color & 0x00FFFFFF;
        int replacement = switch (rgb) {
            case VANILLA_ENABLED_COLOR -> CUSTOM_ENABLED_COLOR;
            case VANILLA_DISABLED_COLOR -> CUSTOM_DISABLED_COLOR;
            case VANILLA_DISABLED_HALF -> CUSTOM_DISABLED_HALF;
            case VANILLA_LEVEL_COLOR -> CUSTOM_LEVEL_COLOR;
            case VANILLA_HIGHLIGHTED_COLOR -> CUSTOM_HIGHLIGHTED_COLOR;
            default -> rgb;
        };
        return (color & 0xFF000000) | replacement;
    }
}

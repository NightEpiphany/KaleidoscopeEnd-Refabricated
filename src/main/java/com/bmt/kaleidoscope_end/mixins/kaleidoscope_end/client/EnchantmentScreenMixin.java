package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end.client;

import com.bmt.kaleidoscope_end.init.KEItem;
import com.bmt.kaleidoscope_end.util.EnchantmentScreenHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.EnchantmentScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.EnchantmentMenu;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentScreen.class)
public abstract class EnchantmentScreenMixin extends AbstractContainerScreen<EnchantmentMenu> {

    @Final
    @Shadow
    private static ResourceLocation[] ENABLED_LEVEL_SPRITES;

    @Final
    @Shadow
    private static ResourceLocation[] DISABLED_LEVEL_SPRITES;

    @Unique
    private static final ResourceLocation[] CUSTOM_ENABLED_SPRITES = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_1"),
            ResourceLocation.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_2"),
            ResourceLocation.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_3")
    };

    @Unique
    private static final ResourceLocation[] CUSTOM_DISABLED_SPRITES = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_1_disabled"),
            ResourceLocation.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_2_disabled"),
            ResourceLocation.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_3_disabled")
    };
    public EnchantmentScreenMixin(EnchantmentMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @ModifyArg(method = "renderBg", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)I"), index = 4)
    private int onDrawString(int color) {
        return EnchantmentScreenHelper.warpColor(color, menu);
    }

    @Redirect(method = "renderBg", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private void redirectBlitSprite(GuiGraphics guiGraphics, ResourceLocation sprite, int x, int y, int width, int height) {
        if (!menu.slots.get(1).getItem().is(KEItem.VOID_CONCH)) {
            guiGraphics.blitSprite(sprite, x, y, width, height);
            return;
        }

        for (int i = 0; i < ENABLED_LEVEL_SPRITES.length; i++) {
            if (sprite.equals(ENABLED_LEVEL_SPRITES[i])) {
                guiGraphics.blitSprite(CUSTOM_ENABLED_SPRITES[i], x, y, width, height);
                return;
            }
        }

        for (int i = 0; i < DISABLED_LEVEL_SPRITES.length; i++) {
            if (sprite.equals(DISABLED_LEVEL_SPRITES[i])) {
                guiGraphics.blitSprite(CUSTOM_DISABLED_SPRITES[i], x, y, width, height);
                return;
            }
        }

        guiGraphics.blitSprite(sprite, x, y, width, height);
    }
}

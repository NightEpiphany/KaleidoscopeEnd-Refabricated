package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end.client;

import com.bmt.kaleidoscope_end.init.KEItem;
import com.bmt.kaleidoscope_end.util.EnchantmentScreenHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.EnchantmentScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.EnchantmentMenu;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Environment(EnvType.CLIENT)
@Mixin(EnchantmentScreen.class)
public abstract class EnchantmentScreenMixin extends AbstractContainerScreen<EnchantmentMenu> {

    @Final
    @Shadow
    private static Identifier[] ENABLED_LEVEL_SPRITES;

    @Final
    @Shadow
    private static Identifier[] DISABLED_LEVEL_SPRITES;

    @Unique
    private static final Identifier[] CUSTOM_ENABLED_SPRITES = new Identifier[]{
            Identifier.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_1"),
            Identifier.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_2"),
            Identifier.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_3")
    };

    @Unique
    private static final Identifier[] CUSTOM_DISABLED_SPRITES = new Identifier[]{
            Identifier.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_1_disabled"),
            Identifier.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_2_disabled"),
            Identifier.fromNamespaceAndPath("kaleidoscope_end", "container/enchanting_table/level_3_disabled")
    };
    public EnchantmentScreenMixin(EnchantmentMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @ModifyArg(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)V"), index = 4)
    private int onDrawString(int color) {
        return EnchantmentScreenHelper.warpColor(color, menu);
    }

    @ModifyArg(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;textWithWordWrap(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/FormattedText;IIIIZ)I"), index = 5)
    private int onDrawEnchantmentName(int color) {
        return EnchantmentScreenHelper.warpColor(color, menu);
    }

    @ModifyArg(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/renderpearl/api/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"), index = 1)
    private Identifier replaceLevelSprite(Identifier sprite) {
        if (!menu.getSlot(1).getItem().is(KEItem.VOID_CONCH)) {
            return sprite;
        }

        for (int i = 0; i < ENABLED_LEVEL_SPRITES.length; i++) {
            if (sprite.equals(ENABLED_LEVEL_SPRITES[i])) {
                return CUSTOM_ENABLED_SPRITES[i];
            }
        }

        for (int i = 0; i < DISABLED_LEVEL_SPRITES.length; i++) {
            if (sprite.equals(DISABLED_LEVEL_SPRITES[i])) {
                return CUSTOM_DISABLED_SPRITES[i];
            }
        }

        return sprite;
    }
}

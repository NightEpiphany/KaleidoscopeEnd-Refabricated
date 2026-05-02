package com.bmt.kaleidoscope_end.init.soupbase;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import com.bmt.kaleidoscope_end.init.KEItem;
import com.github.ysbbbbbb.kaleidoscopecookery.api.recipe.soupbase.ISoupBase;
import com.github.ysbbbbbb.kaleidoscopecookery.client.render.soupbase.SimpleSoupBaseRender;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class DragonBreathBucketSoupBase implements ISoupBase {
    private static final Identifier NAME = Identifier.fromNamespaceAndPath(KaleidoscopeEnd.MOD_ID, "dragon_breath");
    private static final int BUBBLE_COLOR = 0xFF00FF;
    
    private static final Identifier DRAGON_BREATH_TEXTURE = Identifier.fromNamespaceAndPath("minecraft", "block/water_still");

    public DragonBreathBucketSoupBase() {
    }

    @Override
    public Identifier getName() {
        return NAME;
    }

    @Override
    public int getBubbleColor() {
        return BUBBLE_COLOR;
    }

    @Override
    public ItemStack getDisplayStack() {
        return new ItemStack(KEItem.DRAGON_BREATH_BUCKET_ITEM);
    }

    @Override
    public boolean isSoupBase(ItemStack stack) {
        return stack.is(KEItem.DRAGON_BREATH_BUCKET_ITEM);
    }

    @Override
    public ItemStack getReturnContainer(Level level, LivingEntity user, ItemStack soupBase) {
        return new ItemStack(Items.BUCKET);
    }

    @Override
    public boolean isContainer(ItemStack stack) {
        return stack.getItem() == Items.BUCKET;
    }

    @Override
    public ItemStack getReturnSoupBase(Level level, LivingEntity user, ItemStack container) {
        return new ItemStack(KEItem.DRAGON_BREATH_BUCKET_ITEM);
    }

    @Override
    public com.github.ysbbbbbb.kaleidoscopecookery.api.client.render.ISoupBaseRender getRender() {
        return new SimpleSoupBaseRender(DRAGON_BREATH_TEXTURE);
    }
}

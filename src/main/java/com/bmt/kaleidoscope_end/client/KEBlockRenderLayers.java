package com.bmt.kaleidoscope_end.client;

import com.bmt.kaleidoscope_end.init.KEBlocks;
import com.bmt.kaleidoscope_end.init.KEFoodBiteRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;

@Environment(EnvType.CLIENT)
public final class KEBlockRenderLayers {
    private KEBlockRenderLayers() {
    }

    public static void register() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), KEBlocks.DREAM_BERRY_HEAD, KEBlocks.DREAM_BERRY_PLANT, KEBlocks.ENDER_MINT);
        KEFoodBiteRegistry.getRegisteredFoodIds().forEach(id ->
                BlockRenderLayerMap.INSTANCE.putBlock(BuiltInRegistries.BLOCK.get(id), RenderType.cutout()));
    }
}

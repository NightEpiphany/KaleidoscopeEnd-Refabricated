package com.bmt.kaleidoscope_end.client;

import com.bmt.kaleidoscope_end.init.KEBlocks;
import com.bmt.kaleidoscope_end.init.KEFoodBiteRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.registries.BuiltInRegistries;

@Environment(EnvType.CLIENT)
public final class KEBlockRenderLayers {
    private KEBlockRenderLayers() {
    }

    public static void register() {
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, KEBlocks.DREAM_BERRY_HEAD, KEBlocks.DREAM_BERRY_PLANT, KEBlocks.ENDER_MINT);
        KEFoodBiteRegistry.getRegisteredFoodIds().forEach(id ->
                BlockRenderLayerMap.putBlock(BuiltInRegistries.BLOCK.getValue(id), ChunkSectionLayer.CUTOUT));
    }
}

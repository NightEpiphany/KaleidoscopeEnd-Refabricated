package com.bmt.kaleidoscope_end;

import com.bmt.kaleidoscope_end.client.DragonEggShellBlockEntityRenderer;
import com.bmt.kaleidoscope_end.init.KEBlockEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

@Environment(EnvType.CLIENT)
public final class KaleidoscopeEndClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(KEBlockEntities.DRAGON_EGG_SHELL, DragonEggShellBlockEntityRenderer::new);
    }
}

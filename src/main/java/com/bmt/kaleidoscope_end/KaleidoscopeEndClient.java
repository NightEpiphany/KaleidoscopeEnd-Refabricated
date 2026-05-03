package com.bmt.kaleidoscope_end;

import com.bmt.kaleidoscope_end.client.KEBlockRenderLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class KaleidoscopeEndClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KEBlockRenderLayers.register();
    }
}

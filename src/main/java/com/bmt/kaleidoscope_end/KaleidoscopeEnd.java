package com.bmt.kaleidoscope_end;

import com.bmt.kaleidoscope_end.config.MainConfig;
import com.bmt.kaleidoscope_end.event.ChorusFlowerInteractionHandler;
import com.bmt.kaleidoscope_end.event.KEExtraLootTableDrop;
import com.bmt.kaleidoscope_end.event.KEPlayerEvents;
import com.bmt.kaleidoscope_end.init.*;
import com.bmt.kaleidoscope_end.worldgen.KEWorldgen;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KaleidoscopeEnd implements ModInitializer {
    public static final String MOD_ID = "kaleidoscope_end";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        NeoForgeConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.COMMON, MainConfig.init());
        KEEffects.registerEffects();
        KEFoods.init();
        KEBlocks.registerBlocks();
        KEItem.registerItems();
        KEFoodBiteRegistry.init();
        KECreativeTabs.registerTabs();
        KESoupBases.registerAll();LOGGER.info("Kaleidoscope End Initialized");
        ChorusFlowerInteractionHandler.register();
        KEPlayerEvents.register();
        KEExtraLootTableDrop.register();
        KEWorldgen.register();
        LOGGER.info("Kaleidoscope End Initialized");
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}

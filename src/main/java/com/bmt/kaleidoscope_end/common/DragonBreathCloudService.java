package com.bmt.kaleidoscope_end.common;

import com.bmt.kaleidoscope_end.KaleidoscopeEnd;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;

public final class DragonBreathCloudService {
    private static final String BUCKET_PLACED_TAG = KaleidoscopeEnd.MOD_ID + ":bucket_placed_dragon_breath";

    private DragonBreathCloudService() {
    }

    public static void markBucketPlaced(AreaEffectCloud cloud) {
        cloud.addTag(BUCKET_PLACED_TAG);
    }

    public static boolean isCollectible(AreaEffectCloud cloud) {
        return cloud.isAlive()
                && (cloud.getOwner() instanceof EnderDragon || cloud.entityTags().contains(BUCKET_PLACED_TAG));
    }
}

package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.init.soupbase.DragonBreathBucketSoupBase;
import com.github.ysbbbbbb.kaleidoscopecookery.crafting.soupbase.SoupBaseManager;

public final class KESoupBases {
    public static void registerAll() {
        SoupBaseManager.registerSoupBase(new DragonBreathBucketSoupBase());
    }
}
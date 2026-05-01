package com.bmt.kaleidoscope_end.block.crops;

import com.bmt.kaleidoscope_end.init.KEBlocks;
import com.bmt.kaleidoscope_end.init.KEItem;

public class DreamBerryPlantBlock extends KECaveVinesPlantBlock {
    public DreamBerryPlantBlock(Properties properties) {
        super(() -> KEBlocks.DREAM_BERRY_HEAD, properties, () -> KEItem.DREAM_BERRY_ITEM);
    }
}

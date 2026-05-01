package com.bmt.kaleidoscope_end.block.crops;

import com.bmt.kaleidoscope_end.init.KEBlocks;
import com.bmt.kaleidoscope_end.init.KEItem;

public class DreamBerryHeadBlock extends KECaveVinesHeadBlock {
    public DreamBerryHeadBlock(Properties properties) {
        super(properties, () -> KEBlocks.DREAM_BERRY_PLANT, () -> KEItem.DREAM_BERRY_ITEM);
    }
}

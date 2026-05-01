package com.bmt.kaleidoscope_end.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class KEBlockItem extends BlockItem {
    public KEBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public String getDescriptionId() {
        return getOrCreateDescriptionId();
    }
}

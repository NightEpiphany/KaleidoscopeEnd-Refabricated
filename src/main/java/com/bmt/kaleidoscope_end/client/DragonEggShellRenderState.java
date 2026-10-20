package com.bmt.kaleidoscope_end.client;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;

public final class DragonEggShellRenderState extends BlockEntityRenderState {
    public final ItemStackRenderState[] items = new ItemStackRenderState[8];
    public Direction facing = Direction.SOUTH;

    public DragonEggShellRenderState() {
        for (int i = 0; i < items.length; i++) {
            items[i] = new ItemStackRenderState();
        }
    }
}

package com.bmt.kaleidoscope_end.api.event;

import com.github.ysbbbbbb.kaleidoscopecookery.api.event.ActionEvent;
import com.github.ysbbbbbb.kaleidoscopecookery.api.event.IActionCancelable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;

public class EnderManAngerEvent extends ActionEvent implements IActionCancelable {
    private final Player player;
    private final LivingEntity livingEntity;
    public EnderManAngerEvent(EnderMan enderman, Player player) {
        this.livingEntity = enderman;
        this.player = player;
    }


    public Player getPlayer() {
        return player;
    }

    public LivingEntity getEntity() {
        return this.livingEntity;
    }

    @FunctionalInterface
    public interface OnStareEnderMan {
        void onStare(EnderManAngerEvent var1);
    }
}

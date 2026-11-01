package com.bmt.kaleidoscope_end.api.event;

import com.github.ysbbbbbb.kaleidoscopecookery.util.event.CancellableKCEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enderman;
import net.minecraft.world.entity.player.Player;

public class EnderManAngerEvent extends CancellableKCEvent {
    private final Player player;
    private final LivingEntity livingEntity;
    public EnderManAngerEvent(Enderman enderman, Player player) {
        this.livingEntity = enderman;
        this.player = player;
    }


    public Player getPlayer() {
        return player;
    }

    public LivingEntity getEntity() {
        return this.livingEntity;
    }
}

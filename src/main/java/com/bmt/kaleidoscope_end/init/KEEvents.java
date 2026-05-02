package com.bmt.kaleidoscope_end.init;

import com.bmt.kaleidoscope_end.api.event.EnderManAngerEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class KEEvents {

    public static final Event<EnderManAngerEvent.OnStareEnderMan> STARE_ENDERMAN = EventFactory.createArrayBacked(EnderManAngerEvent.OnStareEnderMan.class, (call) -> (action) -> {
        for(EnderManAngerEvent.OnStareEnderMan listener : call) {
            listener.onStare(action);
        }

    });
}

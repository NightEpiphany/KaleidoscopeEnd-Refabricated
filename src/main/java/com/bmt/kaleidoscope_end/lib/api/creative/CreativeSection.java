package com.bmt.kaleidoscope_end.lib.api.creative;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public record CreativeSection(
        Identifier id,
        Identifier tab,
        Component title,
        Identifier banner,
        int priority,
        int topColor,
        int bottomColor,
        int shadowColor,
        int background,
        int textBackground
) implements Comparable<CreativeSection> {
    @Override
    public int compareTo(CreativeSection other) {
        int priorityOrder = Integer.compare(priority, other.priority);
        return priorityOrder != 0 ? priorityOrder : id.compareTo(other.id);
    }
}

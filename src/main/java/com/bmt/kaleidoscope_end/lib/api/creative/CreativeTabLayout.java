package com.bmt.kaleidoscope_end.lib.api.creative;

import java.util.List;

public record CreativeTabLayout(List<CreativeTabBanner> banners) {
    public static final CreativeTabLayout EMPTY = new CreativeTabLayout(List.of());

    public CreativeTabLayout {
        banners = List.copyOf(banners);
    }
}

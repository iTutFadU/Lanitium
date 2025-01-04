package me.itut.lanitium.cookies;

import net.minecraft.nbt.Tag;

import java.util.Map;

public class LanitiumCookie {
    static final LanitiumCookie EMPTY = new LanitiumCookie();

    public final Map<String, Tag> cookie;

    private LanitiumCookie() {
        cookie = Map.of();
    }

    public LanitiumCookie(Map<String, Tag> c) {
        cookie = Map.copyOf(c);
    }
}
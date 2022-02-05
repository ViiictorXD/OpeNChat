package com.vtr.openchat.misc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
@RequiredArgsConstructor
public enum Setting {

    DEFAULT_CHANNEL("default-channel"),
    FORMAT_DEFAULT("format.default");

    private final String path;

    static final Map<Setting, Object> SETTINGS = new HashMap<>();

    public static void put(Setting setting, Object object) {
        SETTINGS.put(setting, object);
    }

    public String asString() {
        return get(String.class);
    }

    @SuppressWarnings("unchecked")
    private <T> T get(Class<T> clazz) {
        final Object value = SETTINGS.get(this);

        try {
            if (!clazz.isAssignableFrom(value.getClass()))
                return null;

            return (T) value;
        } catch (Exception ignored) {
            return null;
        }
    }
}

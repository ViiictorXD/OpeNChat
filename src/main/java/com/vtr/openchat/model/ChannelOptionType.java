package com.vtr.openchat.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
@RequiredArgsConstructor
public enum ChannelOptionType {

    DELAY(Integer.class),
    COST(Double.class),
    COST_WITHDRAW(Boolean.class),
    INSIDE_WORLD_DISTANCE(Double.class),
    SHORTCUT(Boolean.class),
    MUTE(Boolean.class);

    private final Class<?> clazz;

    public String asString(Object value) {
        return get(value);
    }

    public Double asDouble(Object value) {
        return get(value);
    }

    public Integer asInteger(Object value) {
        return get(value);
    }

    public Boolean asBool(Object value) {
        return get(value);
    }

    public <T> T get(Object value) {
        try {
            if (!clazz.isAssignableFrom(value.getClass()))
                return null;

            return (T) value;
        } catch (Exception ignored) {
            return null;
        }
    }
}

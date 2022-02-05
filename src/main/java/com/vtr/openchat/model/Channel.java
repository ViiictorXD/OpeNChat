package com.vtr.openchat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.io.File;
import java.util.Map;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
@AllArgsConstructor
public class Channel {

    private String name;
    @Setter
    private String symbol;
    @Setter
    private String permission;

    private File file;

    private ChatColor color;
    private ChannelType type;

    private Map<ChannelOptionType, Object> options;

    public Object getOption(ChannelOptionType type) {
        return options.get(type);
    }

    public void changeOption(ChannelOptionType type, Object newValue) {
        options.replace(type, newValue);
    }
}

package com.vtr.openchat.bukkit.event.impl;

import com.vtr.openchat.bukkit.event.CancellableEvent;
import com.vtr.openchat.model.Channel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Set;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
@AllArgsConstructor
public class ChatMessageEvent extends CancellableEvent {

    private final Player player;
    private final Channel channel;

    private final Set<Player> recipients;

    private final Map<String, String> tags;

    @Setter
    private String message;

    public boolean addTagValue(String tag, String value) {
        if (tag == null)
            return false;

        if (tags.containsKey(tag))
            return false;

        tags.put(tag, value == null ? "" : value);
        return true;
    }
}

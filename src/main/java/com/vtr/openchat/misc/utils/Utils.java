package com.vtr.openchat.misc.utils;

import com.vtr.openchat.model.ChannelOptionType;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public class Utils {

    public static Integer parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    public static String factoryNewMessage(String input) {
        final StringBuilder builder = new StringBuilder();

        final String[] messageSplit = input.split(" ");
        for (int index = 1; index < messageSplit.length; index++) {
            builder.append(messageSplit[index]).append(" ");
        }
        return builder.toString().trim();
    }

    public static Map<ChannelOptionType, Object> getDefaultOptions() {
        return new HashMap<ChannelOptionType, Object>(){{
            put(ChannelOptionType.DELAY, 3);
            put(ChannelOptionType.COST, 0);
            put(ChannelOptionType.COST_WITHDRAW, false);
            put(ChannelOptionType.INSIDE_WORLD_DISTANCE, 30);
            put(ChannelOptionType.SHORTCUT, true);
            put(ChannelOptionType.MUTE, false);
        }};
    }
    public static ChatColor getColor(String subject) {
        try {
            return ChatColor.getByChar(subject.charAt(0));
        } catch (Exception ignored) {
            return ChatColor.getByChar('7');
        }
    }
}

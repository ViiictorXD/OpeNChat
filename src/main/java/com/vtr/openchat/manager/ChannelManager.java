package com.vtr.openchat.manager;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.bukkit.event.impl.ChatMessageEvent;
import com.vtr.openchat.misc.Setting;
import com.vtr.openchat.model.Channel;
import com.vtr.openchat.model.ChannelOptionType;
import com.vtr.openchat.model.ChannelType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
@RequiredArgsConstructor
public class ChannelManager {

    private final OpenChatPlugin plugin;
    private final Set<Channel> channels = new HashSet<>();

    public Channel match(String name) {
        return channels.stream().filter(channel -> channel.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public Channel getDefaultChannel() {
        return match(Setting.DEFAULT_CHANNEL.asString());
    }

    public void constructor(Channel channel) {
        channels.add(channel);
    }

    public void message(Player player, String message, Channel channel) {
        if (!player.hasPermission(channel.getPermission())) {
            player.sendMessage("§cYou don't have permission to talk in this channel.");
            return;
        }

        if (plugin.getMuteManager().isMuted(player))
            return;

        if ((Boolean) channel.getOption(ChannelOptionType.MUTE)
                && !player.hasPermission("openchat.mute.bypass")) {
            player.sendMessage("§cThat channel is mute.");
            return;
        }

        final Set<Player> recipients = new HashSet<>();
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (!target.hasPermission(channel.getPermission()))
                continue;

            if (channel.getType() == ChannelType.INSIDE_WORLD_DISTANCE
                    && target.getLocation().distanceSquared(player.getLocation()) > (int) channel.getOption(ChannelOptionType.INSIDE_WORLD_DISTANCE))
                continue;

            if (channel.getType() == ChannelType.INSIDE_WORLD_ALL
                    && target.getWorld().getName().equalsIgnoreCase(player.getWorld().getName()))
                continue;

            recipients.add(target);
        }

        final Map<String, String> tags = new HashMap<String, String>() {{
            put("sender", player.getName());
            put("name", channel.getName());
            put("color", channel.getColor().toString());
            put("symbol", channel.getSymbol());
            put("message", message);
        }};

        final ChatMessageEvent event = new ChatMessageEvent(player, channel, recipients, tags, message);
        Bukkit.getScheduler().runTask(plugin, event::callEvent);

        if (event.isCancelled())
            return;

        String format = ChatColor.translateAlternateColorCodes('&', Setting.FORMAT_DEFAULT.asString());

        for (Map.Entry<String, String> entry : event.getTags().entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();

            final String pattern = "{" + key + "}";

            format = format.replace(pattern, value);
        }

        for (Player recipient : recipients)
            recipient.sendMessage(plugin.getFilterManager().getFiltered(format));

        if (recipients.size() == 1 && channel.getType() == ChannelType.INSIDE_WORLD_DISTANCE)
            player.sendMessage("§cThere is nobody around to hear your message!");
    }
}

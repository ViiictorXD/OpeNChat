package com.vtr.openchat.bukkit.listener;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.manager.ChannelManager;
import com.vtr.openchat.misc.utils.Utils;
import com.vtr.openchat.model.Channel;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@RequiredArgsConstructor
public class ChannelListener implements Listener {

    private final OpenChatPlugin plugin;

    @EventHandler
    private void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        final Channel channel = plugin.getChannelManager().getDefaultChannel();
        if (channel == null)
            return;

        plugin.getChannelManager().message(event.getPlayer(), event.getMessage(), channel);
    }

    @EventHandler
    private void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled())
            return;

        final String message = event.getMessage();

        final ChannelManager channelManager = plugin.getChannelManager();
        for (Channel channel : channelManager.getChannels()) {
            final String pattern = "/" + channel.getSymbol() + " ";
            if (!message.startsWith(pattern))
                continue;

            plugin.getChannelManager().message(event.getPlayer(), Utils.factoryNewMessage(message), channel);

            event.setCancelled(true);
            break;
        }
    }
}

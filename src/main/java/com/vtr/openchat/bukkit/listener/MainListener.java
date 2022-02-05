package com.vtr.openchat.bukkit.listener;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.bukkit.event.impl.ChatMessageEvent;
import com.vtr.openchat.misc.FormatTime;
import com.vtr.openchat.model.Mute;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@RequiredArgsConstructor
public class MainListener implements Listener {

    private final OpenChatPlugin plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    private void onChatMessage(ChatMessageEvent event) {
        final Player player = event.getPlayer();
        final Mute mute = plugin.getMuteService().match(player.getName());

        if (mute == null)
            return;

        if (mute.isMute()) {
            System.out.println("B: " + event.isCancelled());
            event.setCancelled(true);

            if (mute.isPermanent()) {
                player.sendMessage("§cYou're muted.");
                return;
            }

            System.out.println("A: " + event.isCancelled());
            player.sendMessage(String.format("§cWait %s to talk again.", FormatTime.buildString(mute.getEnd() - System.currentTimeMillis())));
            return;
        }

        plugin.getMuteService().destructor(mute.getViolator());
    }
}

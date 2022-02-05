package com.vtr.openchat.manager;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.misc.FormatTime;
import com.vtr.openchat.model.Mute;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@RequiredArgsConstructor
public class MuteManager {

    private final OpenChatPlugin plugin;

    public boolean isMuted(Player player) {
        final Mute mute = plugin.getMuteService().match(player.getName());

        if (mute == null)
            return false;

        if (mute.isMute()) {
            if (mute.isPermanent()) {
                player.sendMessage("§cYou're muted.");
                return true;
            }

            player.sendMessage(String.format("§cWait %s to talk again.", FormatTime.buildString(mute.getEnd() - System.currentTimeMillis())));
            return true;
        }

        plugin.getMuteService().destructor(mute.getViolator());
        return false;
    }
}

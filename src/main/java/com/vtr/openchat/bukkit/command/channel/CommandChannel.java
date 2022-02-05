package com.vtr.openchat.bukkit.command.channel;

import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.command.CommandSender;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

public class CommandChannel {

    @Command(
            name = "channel",

            permission = "openchat.commands.channel",

            target = CommandTarget.ALL,
            async = true
    )
    public void invoke(BukkitContext context) {
        final CommandSender sender = context.getSender();
        final String[] args = context.getArgs();

        sender.sendMessage(new String[]{
                "",
                "§a/channel mute <channel name> §7- Mute a channel.",
                "§a/channel unmute <channel name> §7- Unmute a channel.",
                "§a/channel delay <channel name> <new delay in secs> §7- Change a channel delay.",
                "§a/channel permission <channel name> <new permission> §7- Change a channel permission.",
                "§a/channel symbol <channel name> <new symbol> §7- Change a channel symbol.",
                ""
        });
    }
}

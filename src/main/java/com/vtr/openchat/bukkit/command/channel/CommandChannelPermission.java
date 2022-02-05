package com.vtr.openchat.bukkit.command.channel;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.model.Channel;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
public class CommandChannelPermission {

    private final OpenChatPlugin plugin;

    @Command(
            name = "channel.permission",

            permission = "openchat.commands.channel.permission",

            target = CommandTarget.ALL,
            async = true
    )
    public void invoke(BukkitContext context) {
        final CommandSender sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 2) {
            sender.sendMessage("§cUse '/channel permission <channel name> <new permission>' to change a channel permission.");
            return;
        }

        final String channelName = args[0];
        final Channel channel = plugin.getChannelManager().match(channelName);

        if (channel == null) {
            sender.sendMessage("§cChannel not found.");
            return;
        }

        channel.setPermission(args[1]);
        plugin.getChannelLoadable().update(channel);

        sender.sendMessage("§aChannel permission changed.");
    }
}

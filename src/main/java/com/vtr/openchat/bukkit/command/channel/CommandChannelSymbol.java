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
public class CommandChannelSymbol {

    private final OpenChatPlugin plugin;

    @Command(
            name = "channel.symbol",

            permission = "openchat.commands.channel.symbol",

            target = CommandTarget.ALL,
            async = true
    )
    public void invoke(BukkitContext context) {
        final CommandSender sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 2) {
            sender.sendMessage("§cUse '/channel symbol <channel name> <new symbol>' to change a channel symbol.");
            return;
        }

        final String channelName = args[0];
        final Channel channel = plugin.getChannelManager().match(channelName);

        if (channel == null) {
            sender.sendMessage("§cChannel not found.");
            return;
        }

        final String symbol = args[1];

        if (symbol.length() != 1) {
            sender.sendMessage("§cThe channel symbol can only be 1 character.");
            return;
        }

        channel.setSymbol(symbol);
        plugin.getChannelLoadable().update(channel);

        sender.sendMessage("§aChannel symbol changed.");
    }
}

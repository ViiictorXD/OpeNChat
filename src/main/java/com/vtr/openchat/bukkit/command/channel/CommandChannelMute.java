package com.vtr.openchat.bukkit.command.channel;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.model.Channel;
import com.vtr.openchat.model.ChannelOptionType;
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
public class CommandChannelMute {

    private final OpenChatPlugin plugin;

    @Command(
            name = "channel.mute",

            permission = "openchat.commands.channel.mute",

            target = CommandTarget.ALL,
            async = true
    )
    public void invoke(BukkitContext context) {
        final CommandSender sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length < 1) {
            sender.sendMessage("§cUse '/channel mute <channel name>' to mute a channel.");
            return;
        }

        final String channelName = args[0];
        final Channel channel = plugin.getChannelManager().match(channelName);

        if (channel == null) {
            sender.sendMessage("§cChannel not found.");
            return;
        }

        if ((Boolean) channel.getOption(ChannelOptionType.MUTE)) {
            sender.sendMessage("§cThat channel already muted.");
            return;
        }

        channel.changeOption(ChannelOptionType.MUTE, true);
        plugin.getChannelLoadable().update(channel);

        sender.sendMessage("§cChannel has been muted.");
    }
}

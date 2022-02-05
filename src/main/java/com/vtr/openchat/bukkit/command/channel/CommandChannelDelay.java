package com.vtr.openchat.bukkit.command.channel;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.misc.utils.Utils;
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
public class CommandChannelDelay {

    private final OpenChatPlugin plugin;

    @Command(
            name = "channel.delay",

            permission = "openchat.commands.channel.delay",

            target = CommandTarget.ALL,
            async = true
    )
    public void invoke(BukkitContext context) {
        final CommandSender sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 2) {
            sender.sendMessage("§cUse '/channel delay <channel name> <new delay in secs>' to change a channel delay.");
            return;
        }

        final String channelName = args[0];
        final Channel channel = plugin.getChannelManager().match(channelName);

        if (channel == null) {
            sender.sendMessage("§cChannel not found.");
            return;
        }

        final Integer newDelay = Utils.parseInt(args[1]);

        if (newDelay == null || newDelay < 0) {
            sender.sendMessage("§cInsert a valid delay.");
            return;
        }

        channel.changeOption(ChannelOptionType.DELAY, newDelay);
        plugin.getChannelLoadable().update(channel);

        sender.sendMessage("§aChannel delay changed.");
    }
}

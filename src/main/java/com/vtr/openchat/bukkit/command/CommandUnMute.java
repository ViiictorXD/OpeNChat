package com.vtr.openchat.bukkit.command;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.model.Mute;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@RequiredArgsConstructor
public class CommandUnMute {

    private final OpenChatPlugin plugin;

    @Command(
            name = "unmute",

            permission = "openchat.commands.unmute",

            target = CommandTarget.ALL,
            async = true
    )
    public void invoke(BukkitContext context) {
        final CommandSender sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length != 1) {
            sender.sendMessage("§cUse '/unmute <player>' to unmute a player.");
            return;
        }

        final OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        final Mute mute = plugin.getMuteService().match(player.getName());

        if (mute == null || (!player.isOnline() && !player.hasPlayedBefore())) {
            sender.sendMessage("§cPlayer not found.");
            return;
        }

        plugin.getMuteService().destructor(player.getName());

        sender.sendMessage("§c" + player.getName() + " has been unmuted.");
    }
}

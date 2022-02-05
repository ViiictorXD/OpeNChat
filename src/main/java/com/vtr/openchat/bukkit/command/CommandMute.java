package com.vtr.openchat.bukkit.command;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.misc.utils.Utils;
import com.vtr.openchat.model.Mute;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.concurrent.TimeUnit;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@RequiredArgsConstructor
public class CommandMute {

    private final OpenChatPlugin plugin;

    @Command(
            name = "mute",

            permission = "openchat.commands.mute",

            target = CommandTarget.ALL,
            async = true
    )
    public void invoke(BukkitContext context) {
        final CommandSender sender = context.getSender();
        final String[] args = context.getArgs();

        if (args.length < 1) {
            sender.sendMessage("§cUse '/mute <player> [time in secs]' to mute a player.");
            return;
        }

        final OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);

        if (!player.isOnline() && !player.hasPlayedBefore()) {
            sender.sendMessage("§cPlayer not found.");
            return;
        }

        final Mute mute = plugin.getMuteService().match(player.getName());

        if (mute != null) {
            sender.sendMessage("§cThat player already muted.");
            return;
        }

        long end = -1L;
        if (args.length > 1) {
            final Integer seconds = Utils.parseInt(args[1]);

            if (seconds == null || seconds == 0) {
                sender.sendMessage("§cInsert a valid seconds.");
                return;
            }

            end = TimeUnit.SECONDS.toMillis(seconds);
        }

        final long total = end == -1L ? -1L : end + System.currentTimeMillis();
        plugin.getMuteService().constructor(new Mute(
                player.getName(),
                sender.getName(),
                total,
                System.currentTimeMillis()
        ));

        sender.sendMessage("§c" + player.getName() + " has been muted.");
    }
}

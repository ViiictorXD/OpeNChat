package com.vtr.openchat.misc.plugin;

import lombok.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
public abstract class OpenChatImpl extends JavaPlugin implements OpenChat {

    private final Logger log = Logger.getLogger("Minecraft");

    private BukkitFrame commandFrame;

    @Override
    public void onEnable() {
        commandFrame = new BukkitFrame(this);

        enable();
    }

    @Override
    public void onDisable() {
        disable();
    }

    @Override
    public void enable() {
    }

    @Override
    public void disable() {
    }

    @Override
    public void registerCommand(Object... commands) {
        commandFrame.registerCommands(commands);
    }

    @Override
    public void registerListener(Listener... listeners) {
        final PluginManager pluginManager = Bukkit.getPluginManager();

        for (Listener listener : listeners)
            pluginManager.registerEvents(listener, this);
    }
}

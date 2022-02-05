package com.vtr.openchat;

import com.vtr.openchat.bukkit.command.CommandMute;
import com.vtr.openchat.bukkit.command.CommandUnMute;
import com.vtr.openchat.bukkit.command.channel.*;
import com.vtr.openchat.bukkit.listener.ChannelListener;
import com.vtr.openchat.bukkit.listener.MainListener;
import com.vtr.openchat.database.ConnectionFactory;
import com.vtr.openchat.database.sql.SqliteConnectionFactory;
import com.vtr.openchat.manager.FilterManager;
import com.vtr.openchat.manager.MuteManager;
import com.vtr.openchat.misc.Setting;
import com.vtr.openchat.misc.loadable.impl.ChannelLoadable;
import com.vtr.openchat.misc.loadable.impl.FilterLoadable;
import com.vtr.openchat.misc.plugin.OpenChatImpl;
import com.vtr.openchat.manager.ChannelManager;
import com.vtr.openchat.service.mute.MuteService;
import com.vtr.openchat.service.mute.MuteServiceImpl;
import lombok.Getter;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
public class OpenChatPlugin extends OpenChatImpl {

    private ConnectionFactory connectionFactory;

    private FilterManager filterManager;
    private FilterLoadable filterLoadable;

    private MuteManager muteManager;
    private MuteService muteService;

    private ChannelManager channelManager;
    private ChannelLoadable channelLoadable;

    @Override
    public void enable() {
        saveDefaultConfig();

        connectionFactory = new SqliteConnectionFactory(getDataFolder(), "openchat");

        filterManager = new FilterManager();
        filterLoadable = new FilterLoadable(this);

        muteManager = new MuteManager(this);
        muteService = new MuteServiceImpl(getConnectionFactory());

        channelManager = new ChannelManager(this);
        channelLoadable = new ChannelLoadable(this);

        filterLoadable.load();
        channelLoadable.load();

        muteService.createNonExistentTable();

        registerCommand(
                new CommandChannel(),
                new CommandChannelDelay(this),
                new CommandChannelMute(this),
                new CommandChannelUnMute(this),
                new CommandChannelSymbol(this),
                new CommandChannelPermission(this),

                new CommandMute(this),
                new CommandUnMute(this)
        );

        registerListener(
                new ChannelListener(this),
                new MainListener(this)
        );

        registerValues();
    }

    private void registerValues() {
        for (Setting setting : Setting.values()) {
            Setting.put(setting, getConfig().get(setting.getPath()));
        }
    }
}

package com.vtr.openchat.misc.loadable.impl;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.adapter.section.ChannelOptionSectionAdapter;
import com.vtr.openchat.misc.loadable.Loadable;
import com.vtr.openchat.misc.utils.Utils;
import com.vtr.openchat.model.Channel;
import com.vtr.openchat.model.ChannelOptionType;
import com.vtr.openchat.model.ChannelType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@RequiredArgsConstructor
public class ChannelLoadable implements Loadable {

    private final OpenChatPlugin plugin;

    private final ChannelOptionSectionAdapter adapter = new ChannelOptionSectionAdapter();

    @Override
    public void load() {
        File[] files = new File(plugin.getDataFolder(), "channels").listFiles();

        if (files == null || files.length == 0)
            files = createDefaultFiles();

        for (File file : files) {
            final FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            final String name = configuration.getString("name");
            final String symbol = configuration.getString("symbol");
            final String permission = configuration.getString("permission");

            final ChatColor color = Utils.getColor(configuration.getString("color"));

            final ChannelType type = ChannelType.valueOf(StringUtils.upperCase(configuration.getString("type")));

            final Map<ChannelOptionType, Object> options = adapter.adapt(configuration.getConfigurationSection("option"));

            plugin.getChannelManager().constructor(new Channel(
                    name,
                    symbol,
                    permission,

                    file,

                    color,

                    type,
                    options
            ));
        }
    }

    @SneakyThrows
    public void update(Channel channel) {
        final FileConfiguration configuration = YamlConfiguration.loadConfiguration(channel.getFile());

        configuration.set("symbol", channel.getSymbol());
        configuration.set("permission", channel.getPermission());

        for (ChannelOptionType optionType : ChannelOptionType.values())
            configuration.set("option." + StringUtils.lowerCase(optionType.toString()), channel.getOption(optionType));

        configuration.save(channel.getFile());
    }

    private File[] createDefaultFiles() {
        final String resourcePath = "channels" + File.separator + "%s.yml";

        plugin.saveResource(String.format(resourcePath, "global"), false);
        plugin.saveResource(String.format(resourcePath, "local"), false);

        return new File(plugin.getDataFolder(), "channels").listFiles();
    }
}

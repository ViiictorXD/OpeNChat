package com.vtr.openchat.misc.loadable.impl;

import com.vtr.openchat.OpenChatPlugin;
import com.vtr.openchat.adapter.section.FilterSectionAdapter;
import com.vtr.openchat.misc.loadable.Loadable;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@RequiredArgsConstructor
public class FilterLoadable implements Loadable {

    private final OpenChatPlugin plugin;

    private final FilterSectionAdapter adapter = new FilterSectionAdapter();

    @Override
    public void load() {
        final FileConfiguration configuration = plugin.getConfig();
        final ConfigurationSection section = configuration.getConfigurationSection("filters");

        plugin.getFilterManager().constructor(adapter.adapt(section));
    }
}

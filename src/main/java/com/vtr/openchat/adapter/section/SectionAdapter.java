package com.vtr.openchat.adapter.section;

import com.vtr.openchat.adapter.Adapter;
import org.bukkit.configuration.ConfigurationSection;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public interface SectionAdapter<T> extends Adapter<ConfigurationSection, T> {

    @Override
    T adapt(ConfigurationSection section);
}

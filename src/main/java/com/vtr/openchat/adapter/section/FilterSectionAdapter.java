package com.vtr.openchat.adapter.section;

import com.vtr.openchat.model.Filter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashSet;
import java.util.Set;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public class FilterSectionAdapter implements SectionAdapter<Set<Filter>> {

    @Override
    public Set<Filter> adapt(ConfigurationSection section) {
        try {
            final Set<Filter> filters = new HashSet<>();
            for (String key : section.getKeys(false)) {
                filters.add(new Filter(
                        section.getString(key + ".filter"),
                        section.getString(key + ".override")
                ));
            }

            return filters;
        } catch (Exception ignored) {
            return new HashSet<>();
        }
    }
}

package com.vtr.openchat.adapter.section;

import com.vtr.openchat.misc.utils.Utils;
import com.vtr.openchat.model.ChannelOptionType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public class ChannelOptionSectionAdapter implements SectionAdapter<Map<ChannelOptionType, Object>> {

    @Override
    public Map<ChannelOptionType, Object> adapt(ConfigurationSection section) {
        try {
            final Map<ChannelOptionType, Object> options = new HashMap<>();
            for (String key : section.getKeys(false))
                options.put(ChannelOptionType.valueOf(StringUtils.upperCase(key)), section.get(key));

            return options;
        } catch (Exception ignored) {
            return Utils.getDefaultOptions();
        }
    }
}

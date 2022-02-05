package com.vtr.openchat.manager;

import com.vtr.openchat.model.Filter;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
public class FilterManager {

    private final Set<Filter> filters = new HashSet<>();

    public String getFiltered(String subject) {
        for (Filter filter : filters) {
            if (!subject.contains(filter.getPattern()))
                continue;

            subject = subject.replaceAll(filter.getPattern(), filter.getOverride());
        }
        return subject;
    }

    public void constructor(Set<Filter> filters) {
        this.filters.addAll(filters);
    }
}

package com.vtr.openchat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
@AllArgsConstructor
public class Filter {

    private String pattern;
    private String override;
}

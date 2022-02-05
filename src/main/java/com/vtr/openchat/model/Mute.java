package com.vtr.openchat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@Getter
@AllArgsConstructor
public class Mute {

    private String violator;
    private String moderator;

    private long end;
    private long date;

    public boolean isMute() {
        return end == -1L || end > System.currentTimeMillis();
    }

    public boolean isPermanent() {
        return end == -1L;
    }
}

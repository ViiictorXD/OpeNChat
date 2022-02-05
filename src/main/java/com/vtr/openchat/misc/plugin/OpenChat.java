package com.vtr.openchat.misc.plugin;

import org.bukkit.event.Listener;

import java.util.logging.Logger;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public interface OpenChat {

    void enable();

    void disable();

    void registerCommand(Object... commands);

    void registerListener(Listener... listeners);

    Logger getLog();
}

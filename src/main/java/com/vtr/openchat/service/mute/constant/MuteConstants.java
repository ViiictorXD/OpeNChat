package com.vtr.openchat.service.mute.constant;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public interface MuteConstants {

    String MATCH =  "SELECT * FROM oc_mutes WHERE violator = ?";

    String CONSTRUCTOR = "INSERT INTO oc_mutes (violator, moderator, end, date) VALUES (?, ?, ?, ?)";

    String DESTRUCTOR = "DELETE FROM oc_mutes WHERE violator = ?";

    String TABLE = "CREATE TABLE IF NOT EXISTS oc_mutes (" +
            "violator VARCHAR(16) NOT NULL, " +
            "moderator VARCHAR(16) NOT NULL, " +
            "end LONG NOT NULL, " +
            "date LONG NOT NULL" +
            ")";
}

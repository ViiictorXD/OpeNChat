package com.vtr.openchat.adapter.rs;

import com.vtr.openchat.model.Mute;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public class RSMuteAdapter implements RSAdapter<Mute> {

    @Override
    public Mute adapt(ResultSet resultSet) {
        try {
            final String violator = resultSet.getString("violator");
            final String moderator = resultSet.getString("moderator");

            final long end = resultSet.getLong("end");
            final long date = resultSet.getLong("date");

            return new Mute(violator, moderator, end, date);
        } catch (SQLException ignored) {
            return null;
        }
    }
}

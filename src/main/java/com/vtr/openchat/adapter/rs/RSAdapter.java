package com.vtr.openchat.adapter.rs;

import com.vtr.openchat.adapter.Adapter;

import java.sql.ResultSet;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public interface RSAdapter<T> extends Adapter<ResultSet, T> {

    @Override
    T adapt(ResultSet resultSet);
}

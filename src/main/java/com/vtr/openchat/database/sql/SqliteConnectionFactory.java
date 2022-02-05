package com.vtr.openchat.database.sql;

import com.vtr.openchat.database.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@RequiredArgsConstructor
public class SqliteConnectionFactory implements ConnectionFactory {

    private final File file;
    private final String name;

    private Connection connection;

    @SneakyThrows
    @Override
    public Connection getConnection() {
        if (connection == null || connection.isClosed())
            connect();

        return connection;
    }

    @Override
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + file + "/" + name + ".db");
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public boolean hasConnection() {
        try {
            return getConnection() != null && !getConnection().isClosed();
        } catch (SQLException exception) {
            return false;
        }
    }
}

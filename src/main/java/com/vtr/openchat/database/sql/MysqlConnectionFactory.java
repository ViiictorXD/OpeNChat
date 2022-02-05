package com.vtr.openchat.database.sql;

import com.vtr.openchat.database.ConnectionCredential;
import com.vtr.openchat.database.ConnectionFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/

@Getter
@RequiredArgsConstructor
public class MysqlConnectionFactory implements ConnectionFactory {

    private final ConnectionCredential credential;
    private Connection connection;

    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s", credential.getHost(), credential.getDb()),
                    credential.getName(),
                    credential.getPass()
            );
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}

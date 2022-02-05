package com.vtr.openchat.service.mute;

import com.vtr.openchat.adapter.rs.RSMuteAdapter;
import com.vtr.openchat.database.ConnectionFactory;
import com.vtr.openchat.model.Mute;
import com.vtr.openchat.service.mute.constant.MuteConstants;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/

@RequiredArgsConstructor
public class MuteServiceImpl implements MuteService {

    private final ConnectionFactory connectionFactory;
    private final RSMuteAdapter adapter = new RSMuteAdapter();

    @Override
    public Mute match(String violator) {
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(MuteConstants.MATCH);
        ) {
            preparedStatement.setString(1, violator);

            return adapter.adapt(preparedStatement.executeQuery());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void constructor(Mute mute) {
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(MuteConstants.CONSTRUCTOR);
        ) {
            preparedStatement.setString(1, mute.getViolator());
            preparedStatement.setString(2, mute.getModerator());

            preparedStatement.setLong(3, mute.getEnd());
            preparedStatement.setLong(4, mute.getDate());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void destructor(String violator) {
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(MuteConstants.DESTRUCTOR);
        ) {
            preparedStatement.setString(1, violator);

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void createNonExistentTable() {
        try (
                final Connection connection = connectionFactory.getConnection();
                final Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(MuteConstants.TABLE);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

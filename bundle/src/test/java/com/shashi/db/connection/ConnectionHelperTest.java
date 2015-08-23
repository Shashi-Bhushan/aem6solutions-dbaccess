package com.shashi.db.connection;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by shashi on 22/8/15.
 */
public class ConnectionHelperTest {
    ConnectionHelper connectionHelper;
    Connection connection = null;

    @Test
    public void createConnection_CreatingJDBCConnection_SuccessinConnection(){
        connectionHelper = ConnectionHelper.createConnection();
        assertTrue(connectionHelper != null);
    }

    @Test
    public void getConnection_GetConnectionHelperInstance_SuccessInGettingConnection() throws SQLException {
        connection = ConnectionHelper.getConnection();
        assertTrue(connection != null);
    }

    @Test
    public void closeConnection_closeConnectionInstance_SuccessInClosingConnection() throws SQLException {
        ConnectionHelper.close(connection);
        assertTrue(connection == null);
    }
}

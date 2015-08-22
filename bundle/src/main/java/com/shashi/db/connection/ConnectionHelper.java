package com.shashi.db.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by shashi on 17/8/15.
 */
public class ConnectionHelper {

    private String url;
    private static ConnectionHelper connectionHelperInstance;

    private static String driverClass = "com.mysql.jdbc.driver";

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionHelper.class);

    /**
     * private constructor to prevent objectInstantiation
     */
    private ConnectionHelper(){
        try{
            Class.forName(driverClass).newInstance();
            url = "jdbc:mysql://localhost:3306/CQ";
        } catch (ClassNotFoundException e) {
            LOG.error("Class Not Found Exception Thrown. Message is : {}" , e.getMessage());
        } catch (InstantiationException e) {
            LOG.error("Instantiation Exception Thrown. Message is : {}", e.getMessage());
        } catch (IllegalAccessException e) {
            LOG.error("Illegal Argument Exception Thrown. Message is : {}" , e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        if(connectionHelperInstance == null){
            connectionHelperInstance = new ConnectionHelper();
        }
        try {
            return DriverManager.getConnection(connectionHelperInstance.url,"root","root");
        }catch(SQLException event){
            LOG.error("SQL Exception Thrown. Message is : {}" , event.getMessage());
            throw event;
        }
    }

    public static void close(Connection connection)
    {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException e) {
            LOG.error("SQL Exception Thrown. Could not close Connection. Message is : {}" , e.getMessage());
            e.printStackTrace();
        }
    }
}

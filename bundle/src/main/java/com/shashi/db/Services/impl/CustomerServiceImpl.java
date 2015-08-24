package com.shashi.db.Services.impl;

import com.shashi.db.Services.CustomerService;
import com.shashi.db.connection.ConnectionHelper;
import com.shashi.db.constants.PrepareStatements;
import com.shashi.db.model.Customer;

import java.sql.*;

/**
 * Created by shashi on 23/8/15.
 */
public class CustomerServiceImpl implements CustomerService{
    PrepareStatements prepareStatements = new PrepareStatements();

    public int injectData(String firstName, String lastName, String address, String description) {
        Connection connection = null;
        prepareStatements.setTableName(Customer.DB_CREDENTIALS.CUSTOMER_TABLE_NAME);

        int rowCount = 0;

        try{
            connection = ConnectionHelper.getConnection();

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();

            // use ProtectedStatement against SQL Injection
            PreparedStatement preparedStatement = null;

            // set the query and use a PreparedStatement
            String query = prepareStatements.getQueryString("*");
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                rowCount++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getData(CustomerType filter) {
        return null;
    }
}

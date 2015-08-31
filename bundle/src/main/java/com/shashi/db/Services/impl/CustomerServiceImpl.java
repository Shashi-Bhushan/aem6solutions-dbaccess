package com.shashi.db.Services.impl;

import com.shashi.db.Services.CustomerService;
import com.shashi.db.connection.ConnectionHelper;
import com.shashi.db.constants.PrepareStatements;
import com.shashi.db.model.Customer;
import org.apache.felix.scr.annotations.Service;

import java.sql.*;

/**
 * Created by shashi on 23/8/15.
 */
@Service(CustomerService.class)
public class CustomerServiceImpl implements CustomerService{
    PrepareStatements prepareStatements = new PrepareStatements();
    Connection connection = null;

    {
        prepareStatements.setTableName(Customer.DB_CREDENTIALS.CUSTOMER_TABLE_NAME);
    }

    public CustomerServiceImpl() {
        try {
            connection = ConnectionHelper.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int injectData(String firstName, String lastName, String address, String description) {
        int rowCount = 0;

        try{
            connection = ConnectionHelper.getConnection();

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();

            // use ProtectedStatement against SQL Injection
            PreparedStatement selectPreparedStatement = null;

            // set the query and use a PreparedStatement
            String query = prepareStatements.getSelectQueryString("*");
            selectPreparedStatement = connection.prepareStatement(query);

            resultSet = selectPreparedStatement.executeQuery();

            while(resultSet.next()){
                rowCount++;
            }
//
//            // assign PK
            int primaryKey = rowCount + 1;
            String insertQuery = prepareStatements.getInsertQueryString(Customer.DB_FIELD_NAMES.CUSTOMER_ID , Customer.DB_FIELD_NAMES.CUSTOMER_FIRST_NAME,
                    Customer.DB_FIELD_NAMES.CUSTOMER_LAST_NAME , Customer.DB_FIELD_NAMES.CUSTOMER_ADDRESS, Customer.DB_FIELD_NAMES.CUSTOMER_DESCRIPTION );
            PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery);

            insertPreparedStatement.setInt(1 , primaryKey);
            insertPreparedStatement.setString(2 , firstName);
            insertPreparedStatement.setString(3 , lastName);
            insertPreparedStatement.setString(4 , address);
            insertPreparedStatement.setString(5 , description);

            insertPreparedStatement.execute();
            return primaryKey;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getData(CustomerType filter) {
        return null;
    }
}

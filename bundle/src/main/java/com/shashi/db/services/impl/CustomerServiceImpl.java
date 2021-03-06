package com.shashi.db.services.impl;

import com.shashi.db.services.CustomerService;
import com.shashi.db.connection.ConnectionHelper;
import com.shashi.db.constants.PrepareStatements;
import com.shashi.db.model.Customer;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

/**
 * Created by shashi on 23/8/15.
 */
@Component(metatype = true, immediate = true)
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

    public int injectData(String firstName, String lastName, String address, CustomerService.CustomerType description) {
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
            
//            // assign PK
            int primaryKey = rowCount + 1;
            String insertQuery = prepareStatements.getInsertQueryString(Customer.DB_FIELD_NAMES.CUSTOMER_ID , Customer.DB_FIELD_NAMES.CUSTOMER_FIRST_NAME,
                    Customer.DB_FIELD_NAMES.CUSTOMER_LAST_NAME , Customer.DB_FIELD_NAMES.CUSTOMER_ADDRESS, Customer.DB_FIELD_NAMES.CUSTOMER_DESCRIPTION );
            PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery);

            insertPreparedStatement.setInt(1 , primaryKey);
            insertPreparedStatement.setString(2 , firstName);
            insertPreparedStatement.setString(3 , lastName);
            insertPreparedStatement.setString(4 , address);
            insertPreparedStatement.setString(5 , description.toString());

            insertPreparedStatement.execute();
            return primaryKey;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getData(CustomerType filter) {
        try{
            connection = ConnectionHelper.getConnection();

            ResultSet resultSet = null;
            Statement statement = connection.createStatement();

            // use ProtectedStatement against SQL Injection
            PreparedStatement selectPreparedStatement = null;

            // set the query and use a PreparedStatement
            // TODO : change dependency of getSelectQueryString to upperLevel
            PrepareStatements.ConditionBlock block = prepareStatements.new ConditionBlock(Customer.DB_FIELD_NAMES.CUSTOMER_DESCRIPTION,
                    PrepareStatements.Operation.EQUALS, filter.toString());
            String query = prepareStatements.setTableName(Customer.DB_CREDENTIALS.CUSTOMER_TABLE_NAME).getSelectQueryString( block,Customer.DB_FIELD_NAMES.CUSTOMER_FIRST_NAME, Customer.DB_FIELD_NAMES.CUSTOMER_LAST_NAME,
                    Customer.DB_FIELD_NAMES.CUSTOMER_DESCRIPTION,Customer.DB_FIELD_NAMES.CUSTOMER_ADDRESS );

            selectPreparedStatement = connection.prepareStatement(query);

            resultSet = selectPreparedStatement.executeQuery();

            JSONArray array = new JSONArray();
            while(resultSet.next()){
                // TODO: make this dynamic as well
                String fname = resultSet.getString(1);
                String lname = resultSet.getString(2);
                String description = resultSet.getString(3);
                String address = resultSet.getString(4);

                JSONObject object = new JSONObject();

                object.put(Customer.DB_FIELD_NAMES.CUSTOMER_FIRST_NAME, fname);
                object.put(Customer.DB_FIELD_NAMES.CUSTOMER_LAST_NAME, lname);
                object.put(Customer.DB_FIELD_NAMES.CUSTOMER_DESCRIPTION, description);
                object.put(Customer.DB_FIELD_NAMES.CUSTOMER_ADDRESS, address);

                array.put(object);
            }
            return array.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

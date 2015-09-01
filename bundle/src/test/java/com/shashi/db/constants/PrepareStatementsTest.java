package com.shashi.db.constants;

import com.shashi.db.model.Customer;
import com.shashi.db.services.CustomerService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by shashi on 23/8/15.
 */
public class PrepareStatementsTest {
    @Test
    public void getData_getAllData_AllDataQuery(){
        assertEquals("Select * from tab", new PrepareStatements().getSelectQueryString("*"));
    }

    @Test
    public void getData_getCustomerData_AllDataQueryFromCustomerTable(){
        assertEquals("Select * from Customer", new PrepareStatements().setTableName(Customer.DB_CREDENTIALS.CUSTOMER_TABLE_NAME).getSelectQueryString("*"));
    }

    @Test
    public void getData_getCustomerDataWithFields_SomeDataQueryFromCustomerTable(){
        assertEquals("Select fname, lname from Customer", new PrepareStatements().setTableName(Customer.DB_CREDENTIALS.CUSTOMER_TABLE_NAME).getSelectQueryString(Customer.DB_FIELD_NAMES.CUSTOMER_FIRST_NAME, Customer.DB_FIELD_NAMES.CUSTOMER_LAST_NAME));
    }

    @Test
    public void insertData_insertCustomerDataWithFields_SomeDataQueryFromCustomerTable(){
        assertEquals("insert into Customer(id, fname) values (?, ?)", new PrepareStatements().setTableName(Customer.DB_CREDENTIALS.CUSTOMER_TABLE_NAME).getInsertQueryString(Customer.DB_FIELD_NAMES.CUSTOMER_ID, Customer.DB_FIELD_NAMES.CUSTOMER_FIRST_NAME) );
    }

    @Test
    public void insertData_insertCustomerDataWithCustomerType_SomeDataQueryFromCustomerTable(){
        assertEquals("insert into Customer(id, fname, lname, description, address) values (?, ?, ?, ?, ?)", new PrepareStatements().setTableName(Customer.DB_CREDENTIALS.CUSTOMER_TABLE_NAME).getInsertQueryString( Customer.DB_FIELD_NAMES.CUSTOMER_ID, Customer.DB_FIELD_NAMES.CUSTOMER_FIRST_NAME,
                Customer.DB_FIELD_NAMES.CUSTOMER_LAST_NAME, Customer.DB_FIELD_NAMES.CUSTOMER_DESCRIPTION,Customer.DB_FIELD_NAMES.CUSTOMER_ADDRESS ) );
    }

    @Test
    public void getData_getConditionalCustomerDataWithFields_SomeDataQueryFromCustomerTable(){
        PrepareStatements statements = new PrepareStatements();
        PrepareStatements.ConditionBlock block = statements.new ConditionBlock(Customer.DB_FIELD_NAMES.CUSTOMER_DESCRIPTION, PrepareStatements.Operation.EQUALS, CustomerService.CustomerType.ACTIVE_CUSTOMER.toString());

        assertEquals("Select fname, lname from Customer where description=ACTIVE_CUSTOMER",
                statements.setTableName(Customer.DB_CREDENTIALS.CUSTOMER_TABLE_NAME).getSelectQueryString( block,Customer.DB_FIELD_NAMES.CUSTOMER_FIRST_NAME, Customer.DB_FIELD_NAMES.CUSTOMER_LAST_NAME )
        );
    }
}

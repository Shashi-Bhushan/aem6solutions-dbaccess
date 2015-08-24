package com.shashi.db.constants;

import com.shashi.db.model.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by shashi on 23/8/15.
 */
public class PrepareStatementsTest {
    @Test
    public void getData_getAllData_AllDataQuery(){
        assertEquals("Select * from tab", new PrepareStatements().getQueryString("*"));
    }

    @Test
    public void getData_getCustomerData_AllDataQueryFromCustomerTable(){
        assertEquals("Select * from customer", new PrepareStatements().setTableName(Customer.DB_CREDENTIALS.CUSTOMER_TABLE_NAME).getQueryString("*"));
    }

    @Test
    public void getData_getCustomerDataWithFields_SomeDataQueryFromCustomerTable(){
        assertEquals("Select fname,lname from customer", new PrepareStatements().setTableName(Customer.DB_CREDENTIALS.CUSTOMER_TABLE_NAME).getQueryString(Customer.DB_FIELD_NAMES.CUSTOMER_FIRST_NAME + "," + Customer.DB_FIELD_NAMES.CUSTOMER_LAST_NAME));
    }
}

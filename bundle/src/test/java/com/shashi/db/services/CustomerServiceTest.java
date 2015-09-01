package com.shashi.db.services;

import com.shashi.db.model.Customer;
import com.shashi.db.services.impl.CustomerServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by shashi on 23/8/15.
 */
public class CustomerServiceTest {
    CustomerService service;

    @Test
    public void injectData_InjectCustomerDataInDB_SuccessAndDoesNotReturnZERO(){
        service = new CustomerServiceImpl();
        assertTrue(service.injectData("Shashi", "Bhushan", "Chandigarh", CustomerService.CustomerType.ACTIVE_CUSTOMER) != 0);
    }

    @Test
    public void getData_getCustomerDataInDB_SuccessAndDataIsNotNull(){
        service = new CustomerServiceImpl();
        assertTrue( service.getData( CustomerService.CustomerType.ACTIVE_CUSTOMER) != null);
    }
}

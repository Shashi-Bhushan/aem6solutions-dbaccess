package com.shashi.db.services;

/**
 * Created by shashi on 23/8/15.
 */
public interface CustomerService {

    /**
     * Adds a New Customer Record in the CustomerTable
     * @param firstName First Name of the Customer
     * @param lastName Last Name of the Customer
     * @param address Address of the customer
     * @param description Description regarding the Customer Type
     * @return int based on the values
     */
    public int injectData(String firstName, String lastName, String address, CustomerService.CustomerType description);

    /**
     * Retrieves customer data from the Customer table and returns All Customer
     * The filter argument specifies one of the following
     * All Customer - Retrieves All Customer Data
     * Active Customer - Retrieves Only Current Customer Data
     * Past Customer - Retrieves only Past Customer Data
     * @param filter
     * @return
     */
    public String getData(CustomerType filter);

    /**
     * Enumeration for Customer Types
     */
    public enum CustomerType{
        ALL_CUSTOMER,ACTIVE_CUSTOMER,PAST_CUSTOMER;
    }
}

package com.shashi.model;

/**
 * Created by shashi on 17/8/15.
 *
 * This bean holds information regarding the customers.
 */
public class Customer {
    // private members
    private String custId;
    private String custFirstName;
    private String custLastName;
    private String custAddress;
    private String custDesc;

    public interface DB_FIELD_NAMES{
        public static String CUSTOMER_ID = "id";
        public static String CUSTOMER_FIRST_NAME = "fname";
        public static String CUSTOMER_LAST_NAME = "lname";
        public static String CUSTOMER_DESCRIPTION = "description";
        public static String CUSTOMER_ADDRESS = "address";
    }

    public String getCustId() {
        return custId;
    }

    public Customer setCustId(String custId) {
        this.custId = custId;
        return this;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public Customer setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
        return this;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public Customer setCustLastName(String custLastName) {
        this.custLastName = custLastName;
        return this;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public Customer setCustAddress(String custAddress) {
        this.custAddress = custAddress;
        return this;
    }

    public String getCustDesc() {
        return custDesc;
    }

    public Customer setCustDesc(String custDesc) {
        this.custDesc = custDesc;
        return this;
    }
}

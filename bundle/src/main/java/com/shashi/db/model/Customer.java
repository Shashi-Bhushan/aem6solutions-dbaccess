package com.shashi.db.model;

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
        public static final String CUSTOMER_ID = "id";
        public static final String CUSTOMER_FIRST_NAME = "fname";
        public static final String CUSTOMER_LAST_NAME = "lname";
        public static final String CUSTOMER_DESCRIPTION = "description";
        public static final String CUSTOMER_ADDRESS = "address";
    }

    public interface DB_CREDENTIALS{
        public static final String CUSTOMER_TABLE_NAME = "customer";
        public static final String DB_NAME = "CQ";
        public static final String PORT = "3306";
        public static final String DRIVER = "com.mysql.jdbc.Driver";
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

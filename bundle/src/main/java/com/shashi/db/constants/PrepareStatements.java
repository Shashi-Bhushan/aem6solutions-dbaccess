package com.shashi.db.constants;

/**
 * Created by shashi on 23/8/15.
 */
public class PrepareStatements{
    private String tableName;

    /**
     * initializer block sets the tableName once object is created
     * fallback in case a table name is Not Assigned
     */
    {
        tableName = "tab";
    }

    public PrepareStatements setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getData(String filter){
        return "Select " + filter + " from " + tableName;
    }
}

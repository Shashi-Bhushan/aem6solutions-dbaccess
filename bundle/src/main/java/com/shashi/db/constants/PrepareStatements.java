package com.shashi.db.constants;

import com.shashi.db.model.Customer;
import com.shashi.db.services.CustomerService;

/**
 * Created by shashi on 23/8/15.
 */
public class PrepareStatements{
    private String tableName;

    private interface Signs{
        String QUESTION_MARK = "?";
        String START_CIRCLE_BRANCKET = "(";
        String END_CIRCLE_BRACKET = ")";
        String DOUBLE_QUOTE = "\"";
    }

    public interface Operation{
        String EQUALS = "=";
    }

    public class ConditionBlock{
        private String dbField;
        private String operation;
        private String value;

        public ConditionBlock(String dbField, String operation, String value) {
            this.dbField = dbField;
            this.operation = operation;
            this.value = value;
        }

        public String getDbField() {
            return dbField;
        }

        public String getOperation() {
            return operation;
        }

        public String getValue() {
            return value;
        }
    }

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

    /**
     * Gets a plain sql Query without any condition
     * @param filter
     * @return
     */
    public String getSelectQueryString(String... filter){
        StringBuilder fields = new StringBuilder( filter[0]);
        if(filter.length > 1){
            for(int i=1; i< filter.length;i++)
                fields.append( ", " + filter[i] );
        }

        return "Select " + fields + " from " + tableName;
    }

    /**
     * Gets a SQL Query with condition
     * @param condition
     * @param filter
     * @return
     */
    public String getSelectQueryString(PrepareStatements.ConditionBlock condition, String... filter){
        StringBuilder fields = new StringBuilder( filter[0]);
        if(filter.length > 1){
            for(int i=1; i< filter.length;i++)
                fields.append( ", " + filter[i] );
        }

        return "Select " + fields + " from " + tableName + " where " + condition.getDbField() + condition.getOperation() + Signs.DOUBLE_QUOTE + condition.getValue() + Signs.DOUBLE_QUOTE;
    }

    public String getInsertQueryString(String... filter){
        StringBuilder fields = new StringBuilder(Signs.START_CIRCLE_BRANCKET + filter[0]);
        StringBuilder questionMarks = new StringBuilder(Signs.START_CIRCLE_BRANCKET + Signs.QUESTION_MARK);
        if(filter.length > 1){
            for(int i=1; i< filter.length;i++)
            {
                fields.append( ", " + filter[i] );
                questionMarks.append(", " + Signs.QUESTION_MARK);
            }
        }

        fields.append( Signs.END_CIRCLE_BRACKET );
        questionMarks.append( Signs.END_CIRCLE_BRACKET );

        return "insert into " + tableName + fields + " values " + questionMarks;
    }
}

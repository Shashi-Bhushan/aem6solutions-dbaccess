package com.shashi.db.constants;

/**
 * Created by shashi on 23/8/15.
 */
public class PrepareStatements{
    private String tableName;

    private interface Signs{
        String QUESTION_MARK = "?";
        String START_CIRCLE_BRANCKET = "(";
        String END_CIRCLE_BRACKET = ")";
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

    public String getSelectQueryString(String filter){
        return "Select " + filter + " from " + tableName;
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

package main;

import java.sql.*;

public class databaseHandler {

    private static final String DBurl = "jdbc:derby:database/forum";
    public static Connection connection = null; //PRIVATE
    private static Statement statement = null;
    public static databaseHandler handler;


    public databaseHandler() {
        createConnection();
    }

    public static databaseHandler getHandler() {
        if (handler == null) {
            handler = new databaseHandler();
            return handler;
        } else {
            return handler;
        }
    }

    private void createConnection() {
        try {
          //  Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            connection = DriverManager.getConnection(DBurl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean executeAction(String qu) {
        try {
            statement = connection.createStatement();
            statement.execute(qu);
            return true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return false;
    }

    public ResultSet executeQuery(String qu) {
        ResultSet resultset;
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(qu);
            return resultset;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * helper method: create table if none exists
     * first column defaults to primary key
     * @param tableName must begin with letter
     * @param columns number of columns in the table created
     */
    public void createTable(String tableName, int columns,String[] headers) {

        try {
            statement = connection.createStatement();
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet tables = dmd.getTables(null, null, tableName, null);

            if(tables.next()) {
                System.out.println("table " + tableName + " already exists");
            } else {
                String stm = "CREATE TABLE " + tableName + " ("
                        + headers[0] + " VARCHAR(200) primary key)";
                this.statement.execute(stm);

                for (int i = 1; i < columns ; i++) {
                    stm = "ALTER TABLE " + tableName + "\n"
                            + "ADD " + headers[i] + " VARCHAR(200)";
                    this.statement.execute(stm);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

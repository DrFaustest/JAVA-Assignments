package com.bank.gui;
/**
 * DatabaseTest class
 * See App.java for full description
 * @author Scott Faust
 * @version 1.0 - 2023-05-20
 *
 */
import java.sql.*;

public class DatabaseTest {
    private static final String DB_URL = "jdbc:sqlite:bankinggui/src/main/resources/com/bank/gui/db/BankMainDatabase.db";
    private static Connection connection;

    /**
     * This is the main method which makes use of printTable method.
     * Its ment to dump the database to the console for testing purposes
     * @param args
     */
    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            printTable("Customer");
            printTable("Account");
            printTable("AccountTransaction");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void printTable(String tableName) {
        String sql = "SELECT * FROM " + tableName;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(", ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


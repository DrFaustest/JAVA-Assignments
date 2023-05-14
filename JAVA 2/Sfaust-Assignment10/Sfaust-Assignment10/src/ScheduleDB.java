/**
 * 
 * @author Scott Faust
 * @version 1.0
 * 
 * Class:       22_SP_INFO_1531_WW
 * Assignment:  10 - MCC Schedule System
 * Date:        5/9/2023
 * Resources:   Lectures and examples 
 * 
 * Description: The ScheduleDB class is used to connect to the database and perform queries on the database
 * its methods are used by the UserMenu class to perform the searches and add courses to the database
 * 
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ScheduleDB {
    private static Connection connection = null;

    /**
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String dbUrl = "jdbc:sqlite:MCCSchedule.db";
            connection = DriverManager.getConnection(dbUrl);
        } return connection;
    }

    /**
     */
    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * @throws SQLException
     * collects the names of the columns in the Schedule table and returns them as an array of Strings
     * this is scalable because if the table is changed, the program will still work
     * 
     */
    public static String[] getFilterNames() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM Schedule");
        ResultSetMetaData metaData = results.getMetaData();
        Set<String> filterSet = new HashSet<>();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            filterSet.add(metaData.getColumnName(i));
        } return filterSet.toArray(new String[0]);
    }

    /**
     * @throws SQLException
     * collects the unique values for a given column and returns them as an array of Strings
     * this helps the user narrow down their search by reducing the number of results shown
     */
    public static String[] searchUniqueValues(String filter) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet results = statement.executeQuery("SELECT DISTINCT \"" + filter + "\" FROM Schedule");
        List<String> uniqueValues = new ArrayList<>();
        while (results.next()) {
            uniqueValues.add(results.getString(1));
        } return uniqueValues.toArray(new String[0]);
    }

    /**
     * @throws SQLException
     * This is the final level of search, it will print out the results of the search given the column and value
     */
    public static void searchLevelThree(String filter, String value) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM Schedule WHERE \"" + filter + "\" = '" + value + "'");
        System.out.printf("%-10s%-10s%-20s%-10s%-20s%-25s%-15s\n", "Course ID", "Section", "Instructor", "Days", "Time",
                "Campus", "Building/Room");
        while (results.next()) {
            String courseId = results.getString(2);
            String section = results.getString(3);
            String instructor = results.getString(4);
            String days = results.getString(5);
            String time = results.getString(6);
            String campus = results.getString(7);
            String room = results.getString(8);

            System.out.printf("%-10s%-10s%-20s%-10s%-20s%-25s%-15s\n", courseId, section, instructor, days, time,
                    campus, room);
        } UserMenu.mainMenu();
    }


    /**
     * @throws SQLException
     * This method checks to see if a course exists in the database
     */
    public static Boolean courseExists(String courseID) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM Schedule WHERE [Course ID] = '" + courseID + "'");
        return results.next();
    }

    /**
     * @throws SQLException
     * This method checks to see if a course section exists in the database
     */
    public static Boolean courseSectionExists(String courseID, String section) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet results = statement.executeQuery(
                "SELECT * FROM Schedule WHERE [Course ID] = '" + courseID + "' AND Section = '" + section + "'");
        return results.next();
    }

    /**
     * @throws SQLException
     * This method adds a course to the database
     */
    public static void addSchedule(String courseID, String section, String instructor, String days, String time,
            String campus, String buildingRoom) throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("INSERT INTO Schedule VALUES (NULL, '" + courseID + "', '" + section + "', '"
                + instructor + "', '" + days + "', '" + time + "', '" + campus + "', '" + buildingRoom + "')");
        System.out.println("Schedule added successfully");
        UserMenu.mainMenu();
    }

    /**
     * @throws SQLException
     * This method deletes a course from the database
     */
    public static void deleteCourse(String courseID, String section) throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(
                "DELETE FROM Schedule WHERE [Course ID] = '" + courseID + "' AND Section = '" + section + "'");
        System.out.println("Course deleted successfully");
        UserMenu.mainMenu();
    }
}
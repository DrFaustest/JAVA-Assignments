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
 * Description: The UserMenu class is used to display the menu to the user and collect their input
 * it is also used to call the methods in the ScheduleDB class to perform the searches and add courses to the database
 * 
 */
import java.sql.SQLException;
import java.util.Scanner;

// The user menu will be a class that will have a method for each menu option.
public class UserMenu {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @throws SQLException
     * The main user menu that is the root of the program
     */
    public static void mainMenu() throws SQLException {
        System.out.println("Welcome to the MCC Schedule System");
        System.out.println("Please select an option:");
        System.out.println("1. Search");
        System.out.println("2. Add");
        System.out.println("3. Delete");
        System.out.println("4. Exit");
        Integer userChoce = validateUserChoice(4);
        switch (userChoce) {
            case 1:
                searchMenuLevelOne();
                break;
            case 2:
                addMenu();
                break;
            case 3:
                deleteMenu();
                break;
            case 4:
                System.out.println("Thank you for using the MCC Schedule System");
                ScheduleDB.closeConnection(null);
                scanner.close();
                break;
        }

    }

    /**
     * @throws SQLException
     * The First level of the search function populates a menu with the column names of the database
     */
    public static void searchMenuLevelOne() throws SQLException {
        String[] filterNames = ScheduleDB.getFilterNames();
        System.out.println("Please select a filter:");
        for (int i = 0; i < filterNames.length; i++) {
            System.out.println((i + 1) + ". " + filterNames[i]);
        }
        System.out.println((filterNames.length + 1) + ". Back");
        Integer userChoce = validateUserChoice(filterNames.length + 1);
        if (userChoce == filterNames.length + 1) {
            mainMenu();
        } else {
            String[] filterValues = ScheduleDB.searchUniqueValues(filterNames[userChoce - 1]);
            searchMenuLevelTwo(filterValues, filterNames[userChoce - 1]);
        }
    }

    /**
     * @param filterValues
     * @param filterName
     * @throws SQLException
     * The second level of the search function populates a menu with the unique values of the selected column
     */
    public static void searchMenuLevelTwo(String[] filterValues, String filterName) throws SQLException {
        System.out.println("Please select a value:");
        for (int i = 0; i < filterValues.length; i++) {
            System.out.println((i + 1) + ". " + filterValues[i]);
        }
        System.out.println((filterValues.length + 1) + ". Back");
        Integer userChoce = validateUserChoice(filterValues.length + 1);
        if (userChoce == filterValues.length + 1) {
            searchMenuLevelOne();
        } else {
            ScheduleDB.searchLevelThree(filterName, filterValues[userChoce - 1]);
        }
    }

    /**
     * @param maximumValue
     * @return
     * This method is used to validate the user input for the menu options
     */
    public static Integer validateUserChoice(Integer maximumValue) {
        Integer option = null;
        do {
            System.out.print("Enter your choice (1-" + maximumValue + "): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: please enter a number.");
            } else {
                try {
                    option = Integer.parseInt(input);
                    if (option < 1 || option > maximumValue) {
                        System.out.println("Error: please enter a number between 1 and " + maximumValue + ".");
                        option = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: invalid input. Please enter a number.");
                }
            }
        } while (option == null);
        return option;
    }

    /**
     * @param prompt
     * @param regexCheck
     * @return
     * This method is used to validate the user input for the search function
     * it takes a prompt and a regex check to validate the input
     * it also asks the user to confirm their input
     * it helps to prevent SQL injection attacks
     */
    public static String validateUserString(String prompt, String regexCheck) {
        String option = null;
        while (option == null) {
            try {
                System.out.println(prompt);
                option = scanner.nextLine();
                if (!option.matches(regexCheck)) {
                    System.out.println("Invalid input! Please enter a valid string.");
                    option = null;
                    continue;
                }
                System.out.println("You entered: " + option);
                String userResponse = "";
                while (!userResponse.equalsIgnoreCase("Y") && !userResponse.equalsIgnoreCase("N")) {
                    System.out.println("Is this correct? (Y/N)");
                    userResponse = scanner.nextLine();
                    if (!userResponse.equalsIgnoreCase("Y") && !userResponse.equalsIgnoreCase("N")) {
                        System.out.println("Invalid input! Please enter 'Y' or 'N'.");
                    }
                }
                if (userResponse.equalsIgnoreCase("Y")) {
                    return option;
                } else {
                    option = null;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid string");
                scanner.next();
            }
        }
        return option;
    }

    /**
     * @throws SQLException
     * This method is used to add a course from the database
     */
    public static void addMenu() throws SQLException {
        try {
            System.out.println("Please select an option:");
            System.out.println("1. Query a course");
            System.out.println("2. Add a section");
            System.out.println("3. Back");
            Integer userChoce = validateUserChoice(3);

            switch (userChoce) {
                case 1:
                    queryCourseSchedule();
                    addMenu();
                    break;
                case 2:
                    String courseID = validateUserString("Please enter the course ID:(4 Letters Space 4 Digits)",
                            "[a-zA-Z]{4} [0-9]{4}");
                    courseID = courseID.toUpperCase();
                    if (!ScheduleDB.courseExists(courseID)) {
                        System.out.println("Course does not exist please add it first");
                        addMenu();
                    } else {
                        String sectionID = validateUserString(
                                "Please enter the section ID:(1 number and 1 letter or 2 letters)",
                                "[0-9]{1}[a-zA-Z]{1}|[a-zA-Z]{2}");
                        sectionID = sectionID.toUpperCase();
                        String instructor = validateUserString("Please enter the instructor's name:(Last,First inital)",
                                "[a-zA-Z ]+,[a-zA-Z ]");
                        String meetingDays = validateUserString("Please enter the meeting days:(M,T,W,Th,F,online)",
                                "[a-zA-Z,]+");
                        String startTime = validateUserString("Please enter the meeting time (ex. 6:00pm - 9:00pm)",
                                "^$|^([1-9]|1[0-2]):[0-5][0-9][ap]m - ([1-9]|1[0-2]):[0-5][0-9][ap]m$");
                        String campus = validateUserString("Please enter the campus:(Main,Online)", "[a-zA-Z]+");
                        String buildingRoom = validateUserString(
                                "Please enter the building and room number:(ex. Mahoney 115 or Online)",
                                "^$|^[a-zA-Z]+ [0-9]+$");
                        ScheduleDB.addSchedule(courseID, sectionID, instructor, meetingDays, startTime, campus,
                                buildingRoom);
                    }
                    break;
                case 3:
                    mainMenu();
                    break;
            }
        } catch (SQLException e) {
            System.out.println("An error occured while trying to add a section. Please try again.");
            addMenu();
        }
    }

    /**
     * @throws SQLException
     * This method is used to delete a course from the database
     */
    public static void deleteMenu() throws SQLException {
        System.out.println("Please select an option:");
        System.out.println("1. Query a course");
        System.out.println("2. Delete a course");
        System.out.println("3. Back");
        Integer userChoce = validateUserChoice(3);
        switch (userChoce) {
            case 1:
                queryCourseSchedule();
                deleteMenu();
                break;
            case 2:
                String courseID = validateUserString("Please enter the course ID:(4 Letters Space 4 Digits)",
                        "[a-zA-Z]{4} [0-9]{4}");
                courseID = courseID.toUpperCase();
                if (!ScheduleDB.courseExists(courseID)) {
                    System.out.println("Course does not exist");
                    deleteMenu();
                } else {
                    String sectionID = validateUserString(
                            "Please enter the section ID:(1 number and 1 letter or 2 letters)",
                            "[0-9]{1}[a-zA-Z]{1}|[a-zA-Z]{2}");
                    sectionID = sectionID.toUpperCase();
                    if (!ScheduleDB.courseSectionExists(courseID, sectionID)) {
                        System.out.println("Section does not exist");
                        deleteMenu();
                    } else {
                        String userResponse = "";
                        while (!userResponse.equalsIgnoreCase("Y") && !userResponse.equalsIgnoreCase("N")) {
                            System.out.println(
                                    "Are you sure you want to delete this section? This can not be undone! (Y/N)");
                            userResponse = scanner.nextLine();
                            if (!userResponse.equalsIgnoreCase("Y") && !userResponse.equalsIgnoreCase("N")) {
                                System.out.println("Invalid input! Please enter 'Y' or 'N'.");
                            }
                        }
                        if (userResponse.equalsIgnoreCase("Y")) {
                            ScheduleDB.deleteCourse(courseID, sectionID);
                        } else {
                            deleteMenu();
                        }
                    }
                }
                break;
            case 3:
                mainMenu();
                break;
        }
    }

    /**
     * @return
     * @throws SQLException
     * This method is used to query a course from the database
     * it checks if the course exists and if it does it prints the course data
     * if it does not exist it returns an empty string
     */
    public static String queryCourseSchedule() throws SQLException {
        String departmentCode = validateUserString("Please enter the department code(4 Letters):", "[a-zA-Z]{4}");
        departmentCode = departmentCode.toUpperCase();
        String courseNumber = validateUserString("Please enter the course number(4 Numbers):", "[0-9]{4}");
        String CourseID = departmentCode + " " + courseNumber;
        if (ScheduleDB.courseExists(CourseID)) {
            System.out.println("Course exists");
            ScheduleDB.searchLevelThree("Course ID", CourseID);
            return CourseID;
        }
        System.out.println("Course does not exist");
        CourseID = "";
        return CourseID;
    }
}
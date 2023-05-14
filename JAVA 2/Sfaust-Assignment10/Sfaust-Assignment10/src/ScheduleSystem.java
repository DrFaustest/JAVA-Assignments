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
 * Description: The ScheduleSystem is the main class for the MCC Schedule System and is used to call the UserMenu class
 * 
 */
import java.sql.SQLException;

public class ScheduleSystem {
    public static void main(String[] args) throws SQLException{
        try {
            ScheduleDB.getConnection();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        UserMenu.mainMenu();
    }
}

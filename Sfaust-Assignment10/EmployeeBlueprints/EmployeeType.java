/**
 * @author Scott Faust
 * @version 1.0
 * date: 2/19/2023
 * class: 22_WI_INFO_1521_WW
 * resources: Class lecture videos, Murach Java Programming, and the Java API
 * Description:
 * This is the EmployeeType enum. It is a template for an employee type object. It contains the following fields: HOURLY, SALARY, COMMISSION, and the following methods: toString()
 */

package EmployeeBlueprints;

public enum EmployeeType {
    HOURLY, SALARY, COMMISSION;

/**
 * Returns a string representation of the employee type.
 * @return a string representation of the employee type.
 */
    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "Hourly";
            case 1:
                return "Salary";
            case 2:
                return "Commission";
            default:
                return "Unknown";
        }
    }
}

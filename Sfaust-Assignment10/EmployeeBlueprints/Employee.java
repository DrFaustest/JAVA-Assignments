/**
 * @author Scott Faust
 * @version 1.0
 * date: 2/19/2023
 * class: 22_WI_INFO_1521_WW
 * resources: Class lecture videos, Murach Java Programming, and the Java API
 * Description:
 * This is the Employee class. It is a template for an employee object. It contains the following fields: firstName, lastName, employeeNum, department, jobTitle, employeeType, and the following methods: getFirstName(), setFirstName(String fn), getLastName(), setLastName(String ln), getEmployeeNum(), setEmployeeNum(int en), getDepartment(), setDepartment(String dept), getJobTitle(), setJobTitle(String job), getEmployeeType(), setEmployeeType(EmployeeType et), getEmergencyContactList(), setEmergencyContactList(ArrayList<EmergencyContact> ecl), addEmergencyContact(EmergencyContact ec), removeEmergencyContact(EmergencyContact ec), printEmployee(), toString(), and equals(Object o).
 */

package EmployeeBlueprints;

 import java.util.ArrayList;

 /**
  * This class is a template for an employee object.
  */
 public abstract class Employee implements Printable {
 
     /** The employee's first name. */
     private String firstName;
 
     /** The employee's last name. */
     private String lastName;
 
     /** The employee's unique identifier number. */
     private int employeeNum;
 
     /** The department the employee belongs to. */
     private String department;
 
     /** The title of the employee's job. */
     private String jobTitle;
 
     /** The type of employee. */
     private EmployeeType employeeType;
 
     /** A list of emergency contacts for the employee. */
     private ArrayList<EmergencyContact> emergencyContactList;
 
     /**
      * Constructs an Employee object with the given parameters.
      * @param fn the employee's first name
      * @param ln the employee's last name
      * @param en the employee's unique identifier number
      * @param dept the department the employee belongs to
      * @param job the title of the employee's job
      * @param et the type of employee
      */
     public Employee(String fn, String ln, int en, String dept, String job, EmployeeType et) {
         firstName = fn;
         lastName = ln;
         employeeNum = en;
         department = dept;
         jobTitle = job;
         employeeType = et;
         emergencyContactList = new ArrayList<EmergencyContact>();
         percent.setMaximumFractionDigits(3);
     }
 
     /**
      * Constructs an Employee object with the given parameters.
      * @param fn the employee's first name
      * @param ln the employee's last name
      * @param en the employee's unique identifier number
      * @param et the type of employee
      */
     public Employee(String fn, String ln, int en, EmployeeType et) {
         firstName = fn;
         lastName = ln;
         employeeNum = en;
         department = "Unknown";
         jobTitle = "Unknown";
         employeeType = et;
         emergencyContactList = new ArrayList<EmergencyContact>();
         percent.setMaximumFractionDigits(3);
     }
 
     /**
      * Constructs an Employee object that is a copy of the given Employee.
      * @param e the Employee to copy
      */
     public Employee(Employee e) {
         firstName = e.firstName;
         lastName = e.lastName;
         employeeNum = e.employeeNum;
         department = e.department;
         jobTitle = e.jobTitle;
         employeeType = e.employeeType;
         emergencyContactList = new ArrayList<EmergencyContact>();
         percent.setMaximumFractionDigits(3);
     }
 
     /**
      * Constructs a default Employee object.
      */
     public Employee() {
         firstName = "Unknown";
         lastName = "Unknown";
         employeeNum = 0;
         department = "Unknown";
         jobTitle = "Unknown";
         employeeType = EmployeeType.HOURLY;
         emergencyContactList = new ArrayList<EmergencyContact>();
         percent.setMaximumFractionDigits(3);
     }
 
     /**
      * Gets the employee's first name.
      * @return the employee's first name
      */
     public String getFirstName() {
         return firstName;
     }
 
     /**
      * Sets the employee's first name.
      * @param fn the employee's first name
      */
     public void setFirstName(String fn) {
         firstName = fn;
     }
 
     /**
      * Gets the employee's last name.
      * @return the employee's last name
      */
     public String getLastName() {
         return lastName;
     }

    /**
     * Sets the last name of the employee.
     * @param ln the employee's last name
     */
    public void setLastName(String ln) {
        lastName = ln;
    }

    /**
     * Gets the unique identifier number of the employee.
     * @return the employee's identifier number
     */
    public int getEmployeeNumber() {
        return employeeNum;
    }

    /**
     * Sets the unique identifier number of the employee.
     * @param en the employee's new identifier number
     */
    public void setEmployeeNumber(int en) {
        employeeNum = en;
    }

    /**
     * Gets the department the employee belongs to.
     * @return the employee's department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department the employee belongs to.
     * @param dept the employee's new department
     */
    public void setDepartment(String dept) {
        department = dept;
    }

    /**
     * Gets the title of the employee's job.
     * @return the employee's job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Sets the title of the employee's job.
     * @param job the employee's new job title
     */
    public void setJobTitle(String job) {
        jobTitle = job;
    }

    /**
     * Gets the type of employee.
     * @return the employee's type
     */
    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }

    /**
     * Sets the type of employee.
     * @param et the employee's new type
     */
    public void setEmployeeType(EmployeeType et) {
        employeeType = et;
    }

    /**
     * Prints the employee's information.
     */
    @Override
    public void print() {
        System.out.println(toString());
    }

    /**
     * Prints the employee's emergency contact list.
     */
    public void printEmergencyContacts() {
        if (emergencyContactList.size() == 0) {
            System.out.println("No emergency contacts on file.");
        } else {
            for (EmergencyContact contact : emergencyContactList) {
                System.out.println(contact);
            }
        }
    }

    /**
     * Clears the employee's emergency contact list.
     */
    public void clearContacts() {
        emergencyContactList.clear();
    }

    /**
     * Adds a new emergency contact to the employee's list.
     * @param contact the new emergency contact
     */
    public void addNewContact(EmergencyContact contact) {
        emergencyContactList.add(contact);
    }

    /**
     * Gets the employee's emergency contact list.
     * @return the list of emergency contacts
     */
    public ArrayList<EmergencyContact> getEmergencyContactList() {
        return emergencyContactList;
    }

    /**
     * Removes an emergency contact from the employee's list.
     * @param contact the emergency contact to remove
     * @return true if the contact was removed, false otherwise
     */
    public boolean removeContact(EmergencyContact contact) {
        return emergencyContactList.remove(contact);
    }

    /**
     * Removes an emergency contact from the employee's list by index.
     * @param index the index of the contact to remove
     * @return true if the contact was removed, false otherwise
     */
    public boolean removeContact(int index) {
        if (index < 0 || index >= emergencyContactList.size()) {
            return false;
        } else {
            emergencyContactList.remove(index);
            return true;
        }
    }

    /**
     * Resets the fields of an Employee object related to a given week.
     */
    public abstract void resetWeek();

    /**
     * Calculates the weekly pay of an Employee object.
     * @return the calculated weekly pay
     */
    public abstract double calculateWeeklyPay();

    /**
     * Adjusts the rate or salary of an Employee object to give an annual raise.
     */
    public abstract void annualRaise();

    /**
     * Calculates the holiday bonus of an Employee object.
     * @return the calculated holiday bonus
     */
    public abstract double holidayBonus();

    /**
     * Sets the rate or salary of an Employee object.
     * @param pay the new rate or salary to set
     */
    public abstract void setPay(double pay);

    /**
     * Returns a string representation of an Employee object.
     * @return the string representation of an Employee object
     */
    @Override 
    public String toString() {

        return "Name: " + firstName + " " + lastName + "\nID: " + employeeNum + "\nDepartment: " + department + "\nTitle: " + jobTitle + "\nType: " + employeeType.toString();
    }

    /**
     * Determines if an object is equal to an Employee object.
     * @param obj2 the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override 
    public boolean equals(Object obj2) {
        if (obj2 instanceof Employee) {
            Employee e2 = (Employee) obj2;
            return employeeNum == e2.employeeNum;
        } else {
            return false;
        }
    }
}

/*Name: Scott Faust
 * Class: 22_WI_INFO_1521_WW
 * Date: 2/5/2023
 * Resources: Class lecture videos, Murach Java Programming, and the Java API
 * 
 * Description: This class is a template for an employee object. It contains the following fields:
 * firstName, lastName, employeeNum, department, jobTitle, hoursWorked, payRate
 */



import java.text.NumberFormat;
import java.util.ArrayList;

public class Employee {
/*
-firstName : String 
-lastName : String 
-employeeNum : int 
-department : String 
-jobTitle : String 
-hoursWorked : double 
-payRate : double
-currency : NumberFormat
 */
private String firstName;
private String lastName;
private int employeeNum;
private String department;
private String jobTitle;
private double hoursWorked;
private double payRate;
//-emergencyContacts : ArrayList<EmergencyContact>
private ArrayList<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();
private NumberFormat currency = NumberFormat.getCurrencyInstance();

// main constructor that sets up full account
public Employee(String fn, String ln, int en, String dept, String job, double pr)
{
    firstName = fn;
    lastName = ln;
    employeeNum = en;
    department = dept;
    jobTitle = job;
    payRate = pr;
    hoursWorked = 0;
}
//constructor with no balance
public Employee(String fn, String ln, int en)
{
    this(fn, ln, en, "", "", 0);
}
// copy constructor
public Employee(Employee e)
{
    firstName = e.getFirstName();
    lastName = e.getLastName();
    employeeNum = e.getEmployeeNumber();
    department = e.getDepartment();
    jobTitle = e.getJobTitle();
    payRate = e.getPayRate();
    hoursWorked = 0;
}

// default constructor
public Employee()
{
    this("", "", 0, "", "", 0);
}

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public int getEmployeeNumber() {
    return employeeNum;
}

public void setEmployeeNumber(int employeeNum) {
    this.employeeNum = employeeNum;
}

public String getDepartment() {
    return department;
}

public void setDepartment(String department) {
    this.department = department;
}

public String getJobTitle() {
    return jobTitle;
}

public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
}

public double getHoursWorked() {
    return hoursWorked;
}

public void addHours() {
    this.hoursWorked++;
}

public void addHours(double hoursWorked) {
    //check for negative hours
    if (hoursWorked < 0) {
        // the example doesnt't have anthying printed to the terminal indicating an error
    } else {
        this.hoursWorked += hoursWorked;
    }
}

public void resetHours() {
    this.hoursWorked = 0;
}

public double getPayRate() {
    return payRate;
}

public void setPayRate(double payRate) {
    this.payRate = payRate;
}

public double calculateWeeklyPay() {
    return hoursWorked * payRate;
}

public void printEmployee() {
    System.out.println("Name: " + firstName + " " + lastName);
    System.out.println("ID: " + employeeNum);
    System.out.println("Department: " + department);
    System.out.println("Title: " + jobTitle);
    System.out.println("Pay: " + currency.format(payRate));
    System.out.println("Hours Worked: " + hoursWorked);
}

/*
+printEmergencyContacts() : void 
+clearContacts : void 
+addNewContact(EmergencyContact contact) : void 
+getEmergencyContactList() : ArrayList<EmergencyContact> 
+removeContact(EmergencyContact) : boolean 
+removeContact(int index) : boolean 
 */
public void printEmergencyContacts() {
    System.out.println("\n**** Emergency Contacts ****");
    for (EmergencyContact contact : emergencyContacts) {
        System.out.println("\n**** Contact " + (emergencyContacts.indexOf(contact) + 1) + " ****" );
        contact.printContact();
    }
}


public void clearContacts() {
    emergencyContacts.clear();
}

public void addNewContact(EmergencyContact contact) {
    emergencyContacts.add(contact);
}

public ArrayList<EmergencyContact> getEmergencyContactList() {
    return emergencyContacts;
}

public boolean removeContact(EmergencyContact contact) {
    return emergencyContacts.remove(contact);
}

public boolean removeContact(int index) {
    if (index < 0 || index >= emergencyContacts.size()) {
        return false;
    } else {
        emergencyContacts.remove(index);
        return true;
    }
}

}


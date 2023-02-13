/*Name: Scott Faust
 * Class: 22_WI_INFO_1521_WW
 * Date: 2/13/2023
 * Resources: Class lecture videos, Murach Java Programming, and the Java API
 * 
 * Description: This class is a template for an employee object. It contains the following fields:
 * firstName, lastName, employeeNum, department, jobTitle, emergencyContactList, and currency and percent NumberFormat objects
 */

import java.text.NumberFormat;
import java.util.ArrayList;

public abstract class Employee {
//-firstName : String
    private String firstName;
//-lastName : String
    private String lastName;
//-employeeNum : int
    private int employeeNum;
//-department : String
    private String department;
//-jobTitle : String
    private String jobTitle;
//-emergencyContactList : ArrayList<EmergencyContact>
    private ArrayList<EmergencyContact> emergencyContactList;
//#currency: NumberFormat
    protected static NumberFormat currency = NumberFormat.getCurrencyInstance();
//#percent : NumberFormat
    protected static NumberFormat percent = NumberFormat.getPercentInstance();
//<<constructor>>Employee(String fn, String ln, int en, String dept, String job)
    public Employee(String fn, String ln, int en, String dept, String job) {
        firstName = fn;
        lastName = ln;
        employeeNum = en;
        department = dept;
        jobTitle = job;
        emergencyContactList = new ArrayList<EmergencyContact>();
        percent.setMaximumFractionDigits(3);
    }
//<<constructor>>Employee(String fn, String ln, int en)
    public Employee(String fn, String ln, int en) {
        firstName = fn;
        lastName = ln;
        employeeNum = en;
        department = "Unknown";
        jobTitle = "Unknown";
        emergencyContactList = new ArrayList<EmergencyContact>();
        percent.setMaximumFractionDigits(3);
    }
//<<constructor>>Employee(Employee e)
    public Employee(Employee e) {
        firstName = e.firstName;
        lastName = e.lastName;
        employeeNum = e.employeeNum;
        department = e.department;
        jobTitle = e.jobTitle;
        emergencyContactList = new ArrayList<EmergencyContact>();
        percent.setMaximumFractionDigits(3);
    }
//<<constructor>>Employee()
    public Employee() {
        firstName = "Unknown";
        lastName = "Unknown";
        employeeNum = 0;
        department = "Unknown";
        jobTitle = "Unknown";
        emergencyContactList = new ArrayList<EmergencyContact>();
        percent.setMaximumFractionDigits(3);
    }
//+getFirstName() : String
    public String getFirstName() {
        return firstName;
    }
//+setFirstName(String fn) : void
    public void setFirstName(String fn) {
        firstName = fn;
    }
//+getLastName() : String
    public String getLastName() {
        return lastName;
    }
//+setLastName(String ln) : void
    public void setLastName(String ln) {
        lastName = ln;
    }
//+getEmployeeNumber() : int
    public int getEmployeeNumber() {
        return employeeNum;
    }
//+setEmployeeNumber(int en) : void
    public void setEmployeeNumber(int en) {
        employeeNum = en;
    }
//+getDepartment() : String
    public String getDepartment() {
        return department;
    }
//+setDepartment(String dept) : void
    public void setDepartment(String dept) {
        department = dept;
    }
//+getJobTitle() : String
    public String getJobTitle() {
        return jobTitle;
    }
//+setJobTitle(String job) : void
    public void setJobTitle(String job) {
        jobTitle = job;
    }
//+printEmployee() : void
    public void printEmployee() {
        System.out.println(toString());
    }
//+printEmergencyContacts() : void
    public void printEmergencyContacts() {
        if (emergencyContactList.size() == 0) {
            System.out.println("No emergency contacts on file.");
        } else {
            for (EmergencyContact contact : emergencyContactList) {
                System.out.println(contact);
            }
        }
    }
//+clearContacts : void
    public void clearContacts() {
        emergencyContactList.clear();
    }
//+addNewContact(EmergencyContact contact) : void
    public void addNewContact(EmergencyContact contact) {
        emergencyContactList.add(contact);
    }
//+getEmergencyContactList() : ArrayList<EmergencyContact>
    public ArrayList<EmergencyContact> getEmergencyContactList() {
        return emergencyContactList;
    }
//+removeContact(EmergencyContact contact) : boolean
    public boolean removeContact(EmergencyContact contact) {
        return emergencyContactList.remove(contact);
    }
//+removeContact(int index) : boolean
    public boolean removeContact(int index) {
        if (index < 0 || index >= emergencyContactList.size()) {
            return false;
        } else {
            emergencyContactList.remove(index);
            return true;
        }
    }
//+abstract resetWeek() : void
    public abstract void resetWeek();
//+abstract calculateWeeklyPay() : double
    public abstract double calculateWeeklyPay();
//+abstract annualRaise() : void
    public abstract void annualRaise();
//+abstract holidayBonus() : double
    public abstract double holidayBonus();
//+abstract setPay(double pay) : void
    public abstract void setPay(double pay);
//+toString() : String
    @Override 
    public String toString() {
        /*
Name: [firstName] [lastName]
ID: [employeeNum]
Department: [department]
Title: [jobTitle]
         */
        return "Name: " + firstName + " " + lastName + "\nID: " + employeeNum + "\nDepartment: " + department + "\nTitle: " + jobTitle;
    }
//+equals(Object obj2) : boolean
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


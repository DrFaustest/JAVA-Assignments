/**
 * @author Scott Faust
 * @version 1.0
 * date: 2/19/2023
 * class: 22_WI_INFO_1521_WW
 * resources: Class lecture videos, Murach Java Programming, and the Java API
 * Description:
 * This is the SalaryEmployee class. It is a template for a salary employee object. It contains 
 * the following fields: salary, and the following methods: calculateWeeklyPay(), annualRaise(), 
 * holidayBonus(), resetWeek(), setPay(), and toString()
 */

 package EmployeeObjects;

 import EmployeeBlueprints.Employee;
 import EmployeeBlueprints.EmployeeType;
 
 public class SalaryEmployee extends Employee {
     private double salary;    
 
     /**
      * Constructor for creating a new SalaryEmployee instance.
      * 
      * @param fn the employee's first name
      * @param ln the employee's last name
      * @param en the employee's unique identifier number
      * @param dept the department the employee belongs to
      * @param job the title of the employee's job
      * @param s the employee's salary
      */
     public SalaryEmployee(String fn, String ln, int en, String dept, String job, double s) {
         super(fn, ln, en, dept, job, EmployeeType.SALARY);
         salary = s;
     }
 
     /**
      * Calculates the weekly pay of the salary employee.
      * 
      * @return double the weekly pay
      */
     public double calculateWeeklyPay() {
         return salary / 52;
     }
 
     /**
      * Increases the salary of the employee by 6.25%.
      */
     public void annualRaise() {
         salary *= 1.0625;
     }
 
     /**
      * Calculates the holiday bonus for the salary employee, which is 3.365% of the salary.
      * 
      * @return double the holiday bonus
      */
     public double holidayBonus() {
         return salary * 0.03365;
     }
 
     /**
      * Resets the week for the salary employee.
      */
     public void resetWeek() {
         //salary employees don't have to reset their week
     }
 
     /**
      * Sets the salary of the employee.
      * 
      * @param pay the new salary to set
      */
     public void setPay(double pay) {
         salary = pay;
     }
 
     /**
      * Returns a string representation of the salary employee, including their name, ID, department, job title, and salary.
      * 
      * @return String the string representation of the salary employee
      */
     public String toString() {
         return super.toString() + "\nSalary: " + currency.format(salary);
     }
 }
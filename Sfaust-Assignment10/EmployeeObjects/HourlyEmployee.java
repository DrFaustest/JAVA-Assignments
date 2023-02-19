/**
 * @author Scott Faust
 * @version 1.0
 * date: 2/19/2023
 * class: 22_WI_INFO_1521_WW
 * resources: Class lecture videos, Murach Java Programming, and the Java API
 * Description:
 * This is the HourlyEmployee class. It contains the following fields: wage, hoursWorked, and the following methods: increaseHours(), increaseHours(double h), calculateWeeklyPay(), annualRaise(), holidayBonus(), resetWeek(), setPay(double pay), and toString()
 */

 package EmployeeObjects;

 import EmployeeBlueprints.Employee;
 import EmployeeBlueprints.EmployeeType;
 
 public class HourlyEmployee extends Employee {
 
     private double wage;
     private double hoursWorked;
 
     /**
      * Constructor for creating a new HourlyEmployee object.
      * 
      * @param fn   the first name of the employee
      * @param ln   the last name of the employee
      * @param en   the employee number
      * @param dept the department where the employee works
      * @param job  the job title of the employee
      * @param w    the hourly wage of the employee
      */
     public HourlyEmployee(String fn, String ln, int en, String dept, String job, double w) {
         super(fn, ln, en, dept, job, EmployeeType.HOURLY);
         wage = w;
         hoursWorked = 0;
     }
 
     /**
      * Increases the number of hours worked by 1.
      */
     public void increaseHours() {
         hoursWorked++;
     }
 
     /**
      * Increases the number of hours worked by a specified amount.
      * 
      * @param h the number of hours to increase by
      */
     public void increaseHours(double h) {
         if (h > 0)
             hoursWorked += h;
     }
 
     /**
      * Calculates the weekly pay for the employee based on their wage and number of
      * hours worked. If the employee has worked more than 40 hours in a week, they
      * are paid 1.5 times their hourly wage for each hour worked over 40.
      * 
      * @return the amount of pay the employee earned for the week
      */
     public double calculateWeeklyPay() {
         return hoursWorked > 40 ? 40 * wage + (hoursWorked - 40) * wage * 1.5 : hoursWorked * wage;
     }
 
     /**
      * Increases the hourly wage by 5%.
      */
     public void annualRaise() {
         wage *= 1.05;
     }
 
     /**
      * Calculates the holiday bonus for the employee based on their hourly wage.
      * 
      * @return the amount of the holiday bonus
      */
     public double holidayBonus() {
         return wage * 40;
     }
 
     /**
      * Resets the number of hours worked to 0.
      */
     public void resetWeek() {
         hoursWorked = 0;
     }
 
     /**
      * Sets the hourly wage for the employee.
      * 
      * @param pay the new hourly wage for the employee
      */
     public void setPay(double pay) {
         wage = pay;
     }
 
     /**
      * Returns a string representation of the HourlyEmployee object.
      * 
      * @return a string containing the employee's name, ID, department, job title,
      *         hourly wage, and number of hours worked
      */
     public String toString() {
         return super.toString() + "\nWage: " + currency.format(wage) + "\nHours Worked: " + hoursWorked;
     }
 }
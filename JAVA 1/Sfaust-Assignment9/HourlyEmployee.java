/*Name: Scott Faust
 * Class: 22_WI_INFO_1521_WW
 * Date: 2/13/2023
 * Resources: Class lecture videos, Murach Java Programming, and the Java API
 * 
 * Description: This HourlyEmployee class is a template for an hourly employee object. It contains the following fields: wage, hoursWorked, and the following methods: increaseHours(), increaseHours(double h), calculateWeeklyPay(), annualRaise(), holidayBonus(), resetWeek(), and setPay(double pay)
 */



public class HourlyEmployee extends Employee {
//-wage : double
    private double wage;
//-hoursWorked : double
    private double hoursWorked;
//<<constructor>>HourlyEmployee(String fn, String ln, int en, String dept, String job, double w)
    public HourlyEmployee(String fn, String ln, int en, String dept, String job, double w) {
        super(fn, ln, en, dept, job);
        wage = w;
        hoursWorked = 0;
    }
//+increaseHours() : void
    public void increaseHours() {
        hoursWorked++;
    }
//+increaseHours(double h) : void
    public void increaseHours(double h) {
        if (h>0) hoursWorked += h;
    }
//+calculateWeeklyPay() : double
    public double calculateWeeklyPay() {
        return hoursWorked > 40 ? 40 * wage + (hoursWorked - 40) * wage * 1.5 : hoursWorked * wage;
    }
//+annualRaise() : void
    public void annualRaise() {
        wage *= 1.05;
    }
//+holidayBonus() : double
    public double holidayBonus() {
        return wage * 40;
    }
//+resetWeek() : void
    public void resetWeek() {
        hoursWorked = 0;
    }
//+setPay(double pay) : void
    public void setPay(double pay) {
        wage = pay;
    }
//+toString() : String
    public String toString() {
/*
Overrides the Employee toString, but calls the super.toString and adding 
"Wage: " + wage and "Hours Worked: " + hoursWorked at the end each on their new 
line. (This is basically what we had setup for last iteration of the Employee class). Use 
the currency NumberFormat object on the wage variable. 
Name: [firstName] [lastName] 
ID: [employeeNum] 
Department: [department] 
Title: [jobTitle] 
Wage: [wage] 
Hours Worked: [hoursWorked] 
*/
    return super.toString() + "\nWage: " + currency.format(wage) + "\nHours Worked: " + hoursWorked;
    }
}

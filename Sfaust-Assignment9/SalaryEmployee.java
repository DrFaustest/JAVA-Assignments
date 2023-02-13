/*Name: Scott Faust
 * Class: 22_WI_INFO_1521_WW
 * Date: 2/13/2023
 * Resources: Class lecture videos, Murach Java Programming, and the Java API
 * 
 * Description: This SalaryEmployee class is a template for a salary employee object. It contains the following fields: salary, and the following methods: calculateWeeklyPay(), annualRaise(), holidayBonus(), resetWeek(), setPay(), and toString()
 */
 


public class SalaryEmployee extends Employee {
//-salary : double
    private double salary;
//<<constructor>>SalaryEmployee(String fn, String ln, int en, String dept, String job, double s)    
    public SalaryEmployee(String fn, String ln, int en, String dept, String job, double s) {
        super(fn, ln, en, dept, job);
        salary = s;
    }
//+calculateWeeklyPay() : double
    public double calculateWeeklyPay() {
        return salary / 52;
    }
//+annualRaise() : void
    public void annualRaise() {
        salary *= 1.0625;
    }
//+holidayBonus() : double
    public double holidayBonus() {
        return salary * 0.03365;
    }
//+resetWeek() : void
    public void resetWeek() {
        // does nothing "I dont like my job and I dont think im gonna go anymore"
    }
//+setPay(double pay) : void
    public void setPay(double pay) {
        salary = pay;
    }
//+toString() : String
    public String toString() {
        /*
Overrides the Employee toString, but calls the super.toString and adding 
"Salary: " + salary at the end of each on their own new line. Use the NumberFormat 
currency object on the salary variable. 
Name: [firstName] [lastName] 
ID: [employeeNum] 
Department: [department] 
Title: [jobTitle] 
Salary: [salary] 
         */
        return super.toString() + "Salary: " + currency.format(salary);
    }
}

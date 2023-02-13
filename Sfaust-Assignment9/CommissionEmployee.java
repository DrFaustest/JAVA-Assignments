/*Name: Scott Faust
 * Class: 22_WI_INFO_1521_WW
 * Date: 2/13/2023
 * Resources: Class lecture videos, Murach Java Programming, and the Java API
 * 
 * Description: This CommissionEmployee class is a template for a commission employee object. It contains the following fields: sales, rate, and the following methods: increaseSales(), increaseSales(double s), calculateWeeklyPay(), annualRaise(), and holidayBonus()
 */
 


public class CommissionEmployee extends Employee {
//-sales : double
    private double sales;
//-rate : double
    private double rate;
//<<constructor>>CommissionEmployee(String fn, String ln, int en, String dept, String job, double rate)
    public CommissionEmployee(String fn, String ln, int en, String dept, String job, double rate) {
        super(fn, ln, en, dept, job);
        this.rate = rate;
        sales = 0;
    }
//+increaseSales() : void
    public void increaseSales() {
        sales += 100;
    }
//+increaseSales(double s) : void
    public void increaseSales(double s) {
        if (s <= 0) {
        /*System.out.println("Error: Sales must be greater than 0");*/
        } else {
            sales += s;
        }
    }
//+calculateWeeklyPay() : double
    public double calculateWeeklyPay() {
        return sales * rate;
    }
//+annualRaise() : void
    public void annualRaise() {
        rate = rate + 0.002;
    }
//+holidayBonus() : double
    public double holidayBonus() {
        /* no holiday bonus for commission employees, 
        "Mr. Lumbergh told me to talk to payroll and 
        then payroll told me to talk to Mr. Lumbergh and 
        I still havent recieved my paycheck and 
        he took my stapler and never brought it back..."
        */
        return 0;
    }
//+resetWeek() : void
    public void resetWeek() {
        sales = 0;
    }
//+setPay(double pay) : void
    public void setPay(double pay) {
        rate = pay;
    }
//+toString() : String
    public String toString() {
        /*
Overrides the Employee toString, but calls the super.toString, adding "Rate: " 
+ rate and "Sales: " + sales each on a new line. Use the percent and currency 
NumberFormat objects on rate and sales respectively. 
Name: [firstName] [lastName] 
ID: [employeeNum] 
Department: [department] 
Title: [jobTitle] 
Rate: [rate] 
Sales: [sales]
         */
        return super.toString() + "\nRate: " + percent.format(rate) + "\nSales: " + currency.format(sales);
    }
    
}

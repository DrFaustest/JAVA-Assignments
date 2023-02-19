/**
 * @author Scott Faust
 * @version 1.0
 * Class: 22_WI_INFO_1521_WW
 * Date: 2/19/2023
 * Resources: Class lecture videos, Murach Java Programming, and the Java API
 * Description: This is the CommissionEmployee class. It contains the following fields: sales, rate, and the following methods: 
 * increaseSales(), increaseSales(double s), calculateWeeklyPay(), annualRaise(), and holidayBonus()
*/

package EmployeeObjects;

import EmployeeBlueprints.Employee;
import EmployeeBlueprints.EmployeeType;

public class CommissionEmployee extends Employee {
    private double sales;
    private double rate;

    /**
     * Constructor to initialize CommissionEmployee object
     * @param fn the employee's first name
     * @param ln the employee's last name
     * @param en the employee's unique identifier number
     * @param dept the department the employee belongs to
     * @param job the title of the employee's job
     * @param rate the employee's commission rate
     */
    public CommissionEmployee(String fn, String ln, int en, String dept, String job, double rate) {
        super(fn, ln, en, dept, job, EmployeeType.COMMISSION);
        this.rate = rate;
        sales = 0;
    }

    /**
     * Increases sales by 100
     */
    public void increaseSales() {
        sales += 100;
    }

    /**
     * Increases sales by the given amount s
     * @param s the amount to increase sales by
     */
    public void increaseSales(double s) {
        if (s <= 0) {
        //System.out.println("Error: Sales must be greater than 0");
        } else {
            sales += s;
        }
    }

    /**
     * Calculates weekly pay based on the rate and sales
     * @return double the weekly pay
     */
    public double calculateWeeklyPay() {
        return sales * rate;
    }

    /**
     * Increases rate by 0.002 for an annual raise
     */
    public void annualRaise() {
        rate = rate + 0.002;
    }

    /**
     * Commission employees don't receive a holiday bonus
     * @return double 0
     */
    public double holidayBonus() {
        return 0;
    }

    /**
     * Resets sales to 0 at the start of a new week
     */
    public void resetWeek() {
        sales = 0;
    }

    /**
     * Sets the commission rate to the given pay
     * @param pay the new commission rate to set
     */
    public void setPay(double pay) {
        rate = pay;
    }

    /**
     * @return String representation of the CommissionEmployee object
     */
    public String toString() {
        return super.toString() + "\nRate: " + percent.format(rate) + "\nSales: " + currency.format(sales);
    }
} 

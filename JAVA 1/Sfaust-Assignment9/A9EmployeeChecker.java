
// Employee Inheritance Checker for Assignment 8
// Created by Lucas Hartman - MCC - INFO 1521 

import java.text.NumberFormat;

public class A9EmployeeChecker {
    public static void main(String[] args)
    {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        
        // add some employees in
        SalaryEmployee e1 = new SalaryEmployee("Steve", "Rodgers", 3781, "Sales", "Manager", 64325);
        CommissionEmployee e2 = new CommissionEmployee("Clint", "Barton", 6847, "Sales", "Customer Representative", .0265);
        HourlyEmployee e3 = new HourlyEmployee("Tony", "Stark", 5749, "Service", "Lead Service Manager", 32.85);
        
        // initial employee print
        System.out.println("*** Initial Employee Printing ***");
        System.out.println(e1+"\n");
        System.out.println(e2+"\n");
        System.out.println(e3);
        
        // increase employees
        e2.increaseSales(325);
        e3.increaseHours(32);
        e2.increaseSales(-500); // check for negatives
        e3.increaseHours(-10); // check for negatives
        
        // view changes
        System.out.println("\n\n*** Changed Employee sales/hours ***");
        System.out.println(e1+"\n");
        System.out.println(e2+"\n");
        System.out.println(e3);
        
        // show weekly pay
        System.out.println("\n\n*** Calculate Weekly Pay ***");
        System.out.println(e1.getFirstName() + " " + e1.getLastName() + " - Weekly Pay: " + currency.format(e1.calculateWeeklyPay()));
        System.out.println(e2.getFirstName() + " " + e2.getLastName() + " - Weekly Pay: " + currency.format(e2.calculateWeeklyPay()));
        System.out.println(e3.getFirstName() + " " + e3.getLastName() + " - Weekly Pay: " + currency.format(e3.calculateWeeklyPay()));

        // reset week
        e1.resetWeek();
        e2.resetWeek();
        e3.resetWeek();  
        
        // check if reset week worked
        System.out.println("\n\n*** Reset the Week ***");
        e1.printEmployee();
        System.out.println();
        e2.printEmployee();
        System.out.println();
        e3.printEmployee();

        // give holiday bonus
        System.out.println("\n\n*** Give Holiday Bonus***");
        System.out.println(e1.getFirstName() + " " + e1.getLastName() + " - Bonus: " + currency.format(e1.holidayBonus()));
        System.out.println(e2.getFirstName() + " " + e2.getLastName() + " - Bonus: " + currency.format(e2.holidayBonus()));
        System.out.println(e3.getFirstName() + " " + e3.getLastName() + " - Bonus: " + currency.format(e3.holidayBonus()));


        e1.annualRaise();
        e2.annualRaise();
        e3.annualRaise();
        
        // check the annual raise
        System.out.println("\n\n*** Annual Raises ***");
        System.out.println(e1+"\n");
        System.out.println(e2+"\n");
        System.out.println(e3+"\n");



        //Check the equals method
        System.out.println("*** Are employees equal ***");
        if (!e1.equals(e2))
        {
            System.out.println("Employee 1 is not Employee 2, Success!!");
        }
        // new employee but with e1 employee number
        SalaryEmployee e4 = new SalaryEmployee("Joe", "Smith", 3781, "Sales", "Manager", 1000);
        if(e1.equals(e4))
        {
            System.out.println("Employee 1 is the same as this new employee. SUCCESS!!");
        }
    }
}

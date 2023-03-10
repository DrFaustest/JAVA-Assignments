/*
* Name:        Scott Faust
* Class:       22_WI_INFO_1521_WW
* Assignment:  IntrestCalculator.java
* Date:        12/8/2022
* Resources: module 2 lecture series, murach java programming, java api
* Description: given a loan amount and intrest rate, this program will calculate the total intrest owed on the loan
*/

import java.util.Scanner;
import java.text.NumberFormat;
import java.lang.String;

public class IntrestCalculator
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        boolean runProgram = true;
        float loanAmount;
        float intrestRate;
        double intrestTotal;
        String runAgain = "y";
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(3);
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        System.out.println("\nWelcome to the Intrest Calculator\n");
        do
        { 
            runProgram = true;
            if (runAgain.equals("y"))
            {   //the main program loop that will run until the user enters 'n' to exit
                runProgram = true;
                System.out.print("Loan Amount : ");
                loanAmount = input.nextFloat();
                System.out.print("Intrest Rate: ");
                intrestRate = input.nextFloat();
                intrestTotal = (double)Math.round(loanAmount * intrestRate * 100) / 100; 
                //converting the math.round to a double to prevent rounding errors (as seen in the example)
                String loanString = currency.format(loanAmount);
                String intrestString = percent.format(intrestRate);
                String totalString = currency.format(intrestTotal);
                //converting the float values to strings using the NumberFormat class to format the output in a simple way
                System.out.printf("%nLoan Amount  : %20s", loanString);
                System.out.printf("%nIntrest Rate : %20s", intrestString);
                System.out.printf("%nTotal Intrest: %20s", totalString);
                //using the printf method to format the output to the console with a fixed width for each line
                System.out.print("\n\nDo you want to run the program again? (y/n)\n");
                runAgain = input.next(); 
                //using the next() method to get the next string input vs the nextLine() method which gets the
                //next line of input prevents code from breaking if the user enters a string with a space in it.
            }
            else if (runAgain.equals("n"))
            {   //this exits the while loop and ends the program
                runProgram = false;
                System.out.print("\nThank you for using the Intrest Calculator. Goodbye.");
            }
            else
            {
                //this will catch any input that is not 'y' or 'n' and stay in this loop until a valid input is entered, if I added a 
                //counter I could exit after so many failed attempts 
                System.out.print("Invalid input.\nPlease enter 'y' or 'n'.");
                runAgain = input.next();
            }
        }
        while (runProgram == true);
    }
}
// keep getting a resource leak error, I know its because I am not closing the scanner but I am not sure how to do that yet
// spent a long time trying to figure that out this week to no avail. I do understand the concept of closing the scanner and why its important though.



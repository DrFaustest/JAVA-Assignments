


import java.util.Scanner;
import java.text.NumberFormat;

// This is the starter file for Assignment 5 in INFO 1521 Java 1. 
// Modify this, add your header and comments as needed for the file to be understood

public class PaintJobEstimator 
{
    public static void main(String[] args)
    {
        // set up all the helper objects
        Scanner input = new Scanner(System.in);
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        
        
        //Main Program Start
        System.out.println("Welcome to the Happy Accidents Paint Company Estimator\n");


        
        String cont = "y"; // prime input for looping
        while (cont.equalsIgnoreCase("y"))
        {
            // get input for calculations
            System.out.print("Enter paint square footage: ");
            double paintSquareFootage = input.nextDouble();

            System.out.print("Enter cost of paint(per gallon): ");
            double gallonPaintCost = input.nextDouble();

            // calculate paint, labor, and costs associated with the job
            double gallonsOfPaint = calculateGallonsPaint(paintSquareFootage);

            double hoursOfLabor = calculateLabor(paintSquareFootage);

            double totalPaintCost = calculatePaintCost(gallonPaintCost, gallonsOfPaint);

            double laborCharges = calculateLaborCost(hoursOfLabor);

            double totalJob = totalPaintCost + laborCharges;

            
            // Output the results
            System.out.println("Gallons of Paing Required : " + gallonsOfPaint + " gallons");
            System.out.println("Hours of Labor            : " + hoursOfLabor + " hours");
            System.out.println("Cost of Paint             : " + currency.format(totalPaintCost));
            System.out.println("Labor Charges             : " + currency.format(laborCharges));
            System.out.println("\nTotal Cost of the Job     : " + currency.format(totalJob));


            // asks to repeat or not
            System.out.print("Continue? (y/n): ");
            cont = input.next();


        }
    }

            public static int calculateGallonsPaint(double paintSquareFeet){
                int gallonsOfPaint = (int) Math.ceil(paintSquareFeet / 112);
                return gallonsOfPaint;
            }

            public static int calculateLabor(double paintSquareFeet){
                int hoursOfLabor = (int) Math.ceil((paintSquareFeet / 12) * 8);
                return hoursOfLabor;
            }

            public static double calculatePaintCost(double gallonPaintCost, double gallonsOfPaint){
                double totalPaintCost = gallonPaintCost * gallonsOfPaint;
                return totalPaintCost;
            }

            public static double calculateLaborCost(double hoursOfLabor){
                double laborCharges = hoursOfLabor * 35;
                return laborCharges;
            }
 
}

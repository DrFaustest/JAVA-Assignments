/*
 * Name:    Scott Faust
 * Class:   22_WI_INFO_1521_WW
 * Date:    1/17/2023
 * Resources: https://canvas.mccneb.edu/courses/35657/pages/module-5-lectures?module_item_id=2868637 - lecture videos
 *            murach Java
 * Description: Calculates the cost and time of a paint job given sq ft and cost per gallon
 */


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

            double paintSquareFootage = getDouble(input, "Enter square footage of wall space to be painted: ");

            double gallonPaintCost = getDouble(input, "Enter cost of paint per gallon: ");

            // calculate paint, labor, and costs associated with the job
            int gallonsOfPaint = calculateGallonsPaint(paintSquareFootage);

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
                int hoursOfLabor = (int) Math.round((paintSquareFeet / 112 * 8)); //assignment says paintSqFt/12*8 not 112 (this math works out right)
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

            public static double getDouble(Scanner sc, String prompt){
                double d = 0.0;
                boolean isValid = false;
                while (isValid == false){
                    System.out.print(prompt);
                    if (sc.hasNextDouble()){
                        d = sc.nextDouble();
                        isValid = true;
                    }
                    else{
                        System.out.println("Error! Invalid decimal value. Try again.");
                    }
                    sc.nextLine();
                }
                return d;
            }
 
}

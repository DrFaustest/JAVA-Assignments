/*
 * Name:        Scott Faust
 * Class:       22_WI_INFO_1521_WW
 * Assignment:  ShapeCalculator.java
 * Date:        12/2/2022
 * Resources:   https://www.w3schools.com/java/java_user_input.asp -- for user input examples
 *              https://canvas.mccneb.edu/courses/35657/pages/module-1-lectures?module_item_id=2868610 -- for lectures and in-class examples
 * 
 * Description: This program will calculate the area of a circle, rectangle, or triangle based on user input using the console.
 */

// Import the Scanner class
import java.util.Scanner;

// Create the class
public class ShapeCalculator 
{
    // Create the main method
    public static void main(String[] args) 
    {
        // Create the Scanner object to read user input and assign it to a variable
        Scanner input = new Scanner(System.in);
        // Create variables to hold the user's choice
        int choice;
        double area;
        double length;
        double width;
        double height;
        double base;
        double radius;
        double base1;
        double base2;
        double height1;
        // Create a do-while loop to allow the user to continue calculating areas until they choose to exit
        do 
        {
            //display menu and get user choice
            System.out.println("Shape Calculator");
            System.out.println("1. Rectangle");
            System.out.println("2. Triangle");
            System.out.println("3. Trapazoid");
            System.out.println("4. Circle");
            System.out.println("5. Quit"+ "\n");
            System.out.println("Which area would you like to calculate: " );
            choice = input.nextInt();
            // Create a switch statement to determine which shape the user wants to calculate the area of and display the result
            if (choice == 1) 
            {
                System.out.println("Enter the length: ");
                length = input.nextDouble();
                System.out.println("Enter the width: ");
                width = input.nextDouble();
                area = length * width;
                System.out.println("The area is " + area + "\n");
            }
            else if (choice == 2) 
            {
                System.out.println("Enter the base: ");
                base = input.nextDouble();
                System.out.println("Enter the height: ");
                height = input.nextDouble();
                area = (base * height) / 2;
                System.out.println("The area is " + area + "\n");
            }

            else if (choice == 3) 
            {
                System.out.println("Enter the base1: ");
                base1 = input.nextDouble();
                System.out.println("Enter the base2: ");
                base2 = input.nextDouble();
                System.out.println("Enter the height: ");
                height1 = input.nextDouble();
                area = ((base1 + base2) / 2) * height1;
                System.out.println("The area is " + area + "\n" );
            }

            else if (choice == 4) 
            {
                System.out.println("Enter the radius: ");
                radius = input.nextDouble();
                area = Math.PI * Math.pow(radius, 2);
                System.out.println("The area is " + area + "\n");
            }

            else if (choice == 5) 
            {
                System.out.println("Goodbye"+ "\n"); 
            }
            // Create a default case to handle invalid user input
            else 
            {
                System.out.println("Invalid choice" + "\n");
            }
        }
        // Create a while loop to continue the program until the user chooses to exit
        while (choice != 5);
    }
}


/*
 * Name:        Scott Faust
 * Class:       22_WI_INFO_1521_WW
 * Assignment:  GCD.java
 * Date:        11/6/2023
 * Resources:   Module 4 lectures, https://canvas.mccneb.edu/courses/35657/files/5222508?module_item_id=2868675
 *              Murach's Java Programming TEXTBOOK
 * 
 * Description: This program calculates the greatest common divisor of two positive integers.
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class GCD {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) { // Loop program, exit if user enters anything other than 'y' or 'Y' by using the break statement to exit the loop
      // Get positive number input from user, only exit loop when positive numbers are entered
      System.out.println("Enter two positive integers: ");
      int x = 0; // initialize x and y to 0 so that the while loop will run at least once
      int y = 0;

      while (x <= 0) {
        try {
          System.out.print("x: ");
          x = scanner.nextInt();
        } catch (InputMismatchException e) { // catch invalid input and prompt user to enter a positive integer
          System.out.println("Error: input must be a positive integer.");
          scanner.next(); // discard invalid input
        }
      }
      
      while (y <= 0) {
        try {
          System.out.print("y: ");
          y = scanner.nextInt();
        } catch (InputMismatchException e) {
          System.out.println("Error: input must be a positive integer.");
          scanner.next(); // discard invalid input
        }
      }
      

      // Calculate GCD
      while (x != 0) {
        while (y>=x) {
          y=y-x;
        }
        int temp = y;
        y = x;
        x = temp;
      }
      System.out.println("GCD: " + y);

      // Loop program, exit if user enters anything other than 'y' or 'Y' by using the break statement to exit the loop
      System.out.print("Do you want to continue? (y/n) ");
      String input = scanner.next();
      if (!input.equalsIgnoreCase("y")) {
        break;
      }
    }
  }
}
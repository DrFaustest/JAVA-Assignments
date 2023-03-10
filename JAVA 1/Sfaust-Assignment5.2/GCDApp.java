/*
 * Name:    Scott Faust
 * Class:   22_WI_INFO_1521_WW
 * Date:    1/17/2023
 * Resources: https://canvas.mccneb.edu/courses/35657/pages/module-5-lectures?module_item_id=2868637 - lecture videos
 *            murach Java
 * Description: recursivly finds the greatest common divisor of two numbers
 */

import java.util.Scanner;

public class GCDApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the first number: ");
        int firstNumber = Integer.parseInt(sc.nextLine());

        System.out.print("Enter the second number: ");
        int secondNumber = Integer.parseInt(sc.nextLine());

        System.out.println("Iterative GCD...");
        int result = iterGCD(firstNumber, secondNumber);
        System.out.println(result);

        System.out.println("Recursive GCD...");
        result = recGCD(firstNumber, secondNumber);
        System.out.println(result);
        System.out.println();
    }    

    // iterative
    public static int iterGCD(int a, int b) {
        System.out.println("Iterative solution here...");
        int remainder = a % b; // initial division
        while (remainder != 0) // checks if remainder is 0, once it is we have our GCD
        {
            a = b; // overwrite a with b
            b = remainder; // overwrite b with remainder
            remainder = a % b; // perform division again to check GCD
        }
        return b;
    }

    // recursive
    public static int recGCD(int a, int b) {
        if (b == 0) return a;   // if b is 0 then we have our GCD and return exits the method 
        else return recGCD(b, a % b); //feeding the varriables in reversed order back to the method reassings the values of a and b to the oppisite of what they were before
    }
}
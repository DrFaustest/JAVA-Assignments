/*
 * Name:        Scott Faust
 * Class:       22_WI_INFO_1521_WW
 * Assignment:  Maze Final Project
 * Date:        1/27/2023
 * Resources:  java documentation
 * 
 * Description: A console UI for the maze solver.
 */

import java.util.Scanner;

public class MazeDriver {
    public static int[][] m1 = {
        {1,1,1,1,1,1,1,1,1,1,1,1}, 
        {1,0,0,0,1,0,0,0,0,0,0,1}, 
        {-1,0,1,0,1,0,1,1,1,1,0,1}, 
        {1,1,1,0,1,0,0,0,0,1,0,1}, 
        {1,0,0,0,0,1,1,1,0,1,0,-1}, 
        {1,1,1,1,0,1,0,1,0,1,0,1}, 
        {1,0,0,1,0,1,0,1,0,1,0,1}, 
        {1,1,0,1,0,1,0,1,0,1,0,1}, 
        {1,0,0,0,0,0,0,0,0,1,0,1}, 
        {1,1,1,1,1,1,0,1,1,1,0,1}, 
        {1,0,0,0,0,0,0,1,0,0,0,1}, 
        {1,1,1,1,1,1,1,1,1,1,1,1}};
    public static int[][] m2 = {
        {1,1,1,1,1,1,1,1,1,1,1,1}, 
        {1,0,0,0,1,0,0,0,1,1,0,-1}, 
        {1,0,1,0,0,0,1,0,0,0,0,1}, 
        {1,0,1,1,1,1,0,1,0,1,0,1}, 
        {1,0,0,0,1,1,0,0,0,0,0,1}, 
        {1,1,1,0,1,1,1,1,0,1,0,1}, 
        {-1,0,0,0,0,0,0,0,0,0,1,1}, 
        {1,1,1,1,1,1,1,1,1,1,1,1}};
    public static int[][] m3 = {
        {1,1,1,1,1,1,1,1,1}, 
        {1,0,1,0,1,0,0,0,1}, 
        {1,0,0,0,1,0,1,1,1}, 
        {1,1,1,0,1,0,1,0,-1}, 
       {-1,0,0,0,0,0,1,0,1}, 
        {1,1,0,1,0,1,1,0,1}, 
        {1,0,0,1,0,1,0,0,1}, 
        {1,1,0,1,0,1,0,0,1}, 
        {1,1,1,1,1,1,1,1,1}}; 
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int[][] maze = null;
        Maze m = null;
        MazeSolver ms = null;
        boolean solved = false;
        
        System.out.println("Welcome to the Maze Solver!");
        System.out.println("Please select a maze to solve:");
        System.out.println("1. Maze 1");
        System.out.println("2. Maze 2");
        System.out.println("3. Maze 3");
        System.out.println("4. Quit");
        choice = input.nextInt();
        
        while (choice != 4) {
            if (choice == 1) {
                maze = m1;
            } else if (choice == 2) {
                maze = m2;
            } else if (choice == 3) {
                maze = m3;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            
            m = new Maze(maze);
            ms = new MazeSolver(m);
            solved = ms.solveMaze();
            if (solved) {
                System.out.println("The maze was solved!");
                m.printMaze();
            } else {
                System.out.println("The maze was not solved.");
                m.printMaze();
            }
            System.out.println("Please select a maze to solve:");
            System.out.println("1. Maze 1");
            System.out.println("2. Maze 2");
            System.out.println("3. Maze 3");
            System.out.println("4. Quit");
            choice = input.nextInt();
        }
        System.out.println("Goodbye!");
    }
}


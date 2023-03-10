/*
 * Name:        Scott Faust
 * Class:       22_WI_INFO_1521_WW
 * Assignment:  Maze Final Project
 * Date:        1/27/2023
 * Resources:  java documentation
 * 
 * Description: A class to solve a given maze.
 */


public class MazeSolver {
    private Maze maze;
    private int finishColumn;

    public MazeSolver(Maze m) {
        maze = m;
        finishColumn = maze.getExitColumn();
    }

    public boolean solveMaze() {
        int startRow = maze.getEntranceRow();
        return solveMaze(startRow, 0);
    }
    //console logs are commented out, but can be used to see the algorithm in action for debugging purposes
    private boolean solveMaze(int r, int c) {
        if (maze.isOpenSpace(r, c)) {
            //System.out.println("Visiting cell (" + r + ", " + c + ")");
            if (c == finishColumn) {
                //System.out.println("Found exit at (" + r + ", " + c + ")");
                return true;
            } else {
                maze.setCell(r, c, 2);
                //System.out.println("Marking cell (" + r + ", " + c + ") as visited");
                if (solveMaze(r - 1, c)) {
                    return true;
                } else if (solveMaze(r, c - 1)) {
                    return true;
                } else if (solveMaze(r, c + 1)) {
                    return true;
                } else if (solveMaze(r + 1, c)) {
                    return true;
                } else {
                    //If none of the above recursive calls work we need to set the space back to a '0' value as it is not the path taken to find the exit. this results in a stack overflow error as the program cannot diferenchate  valid and invalid paths
                    maze.setCell(r, c, 3);
                    //System.out.println("No path found, marking cell (" + r + ", " + c + ") as blocked");
                }
            }
        } else {
            //System.out.println("Cell (" + r + ", " + c + ") is a wall or has been visited before");
        }
        return false;
    }
}    

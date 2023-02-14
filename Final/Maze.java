

public class Maze {
    //-int [][] map
    private int[][] map;
    //-int entranceRow
    private int entranceRow;

    //<<constructor>> Maze(int[][] m)
    public Maze(int[][] m) {
        map = m;
        entranceRow = findEntranceRow();
    }
    //-findEntranceRow() : int
    private int findEntranceRow() {
        for (int i = 0; i < map.length; i++) {
            if (map[i][0] == -1) {
                return i;
            }
        }
        return -1;
    }
    
    //+getEntranceRow() : int
    public int getEntranceRow() {
        return entranceRow;
    }
    //+getExitColumn() : int
    public int getExitColumn() {
        return map[0].length - 1;
    }
    //+getCell(int r, int c) : int
    public int getCell(int r, int c) {
        return map[r][c];
    }
    //+setCell(int r, int c, int val) : void
    public void setCell(int r, int c, int val) {
        map[r][c] = val;
    }
    //+isOpenSpace(int r, int c) : boolean
    public boolean isOpenSpace(int r, int c) {
        // catch out of bounds
        if (r < 0 || r >= map.length || c < 0 || c >= map[r].length) {
            return false;
        }
        // return true if the cell is an open space or the entrance/exit
        return map[r][c] == 0 || map[r][c] == -1;
    }
    
    //+printMaze()
    //-1 = entrance/exit
    //0 = open space
    //1 = wall
    //2 = visited
    public void printMaze() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == -1) {
                    System.out.print("[]");
            } else if (map[i][j] == 0) {
                    System.out.print("  ");
                } else if (map[i][j] == 1) {
                    System.out.print("**");
                } else if (map[i][j] == 2) {
                    System.out.print("@@");
                }
            }
            System.out.println();
        }
    }
}

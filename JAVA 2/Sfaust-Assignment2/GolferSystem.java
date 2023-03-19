import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * NAME:
 * CLASS:
 *
 * RESOURCE:
 *
 * This class is the main runner for the golf stat system. It reads in the text file
 * and loads it up to a list for the user to add, delete and save back out.
 *
 * @author Lucas Hartman
 * @version 1.0.0 - 02/17/23
 */
public class GolferSystem {
    /**
     * Main method for the System
     * @param args String inputs form command line
     * @throws GolfersException
     */
    public static void main(String[] args) throws GolfersException {
        Scanner input = new Scanner(System.in);
        ArrayList<Golfer> golfers = new ArrayList<>();
        int choice = 0;

        // ************* read in the golfer stat file ********************
        try {
            readInGolfers(golfers);
        }
        catch (GolfersException e) {
            System.out.println(e.getMessage());
        }


        // loop through
        while (choice != 4) {
            // menu printout
            System.out.println(" **** Golfer System Menu **** ");
            System.out.println("1.View Golfers\n2.Add Golfers\n3.Delete Golfer\n4.Save and Exit");
            choice = getInt("Selection: ", input);
            while (choice < 1 || choice > 4) {
                System.out.println("Error, not an option.");
                choice = getInt("Selection: ", input);
            }

            // call respective methods for operation
            switch (choice) {
                case 1:
                    printGolfers(golfers, false);
                    break;
                case 2:
                    addGolfer(golfers, input);
                    break;
                case 3:
                    deleteGolfer(golfers, input);
                    break;
                case 4:
                    try {
                    writeOutGolfers(golfers);
                    }
                    catch (GolfersException e) {
                        System.out.println(e.getMessage());
                    }
                    // fall-through
                default:
                    // exit the program
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * This method reads in the GolfStats.txt file and loads them into the ArrayList.
     * @param list an ArrayList that holds the golfer's data
     * @throws GolfersException if there is an issue with the file
     */
    private static void readInGolfers(ArrayList<Golfer> list) throws GolfersException {
        // open file to read
        try (BufferedReader emailsIn = new BufferedReader(new FileReader("GolfStats.txt"))) {

            String line = emailsIn.readLine(); // read first line
            while (line != null)  // read until end of file
            {
                String[] fields = line.split("-"); // split via tab
                // index 0 - name
                // index 1 - string with commas to split into array and convert to
                ArrayList<Integer> scoreArray = new ArrayList<>();
                if (fields.length == 2) {
                    String[] scores = fields[1].split(",");

                    for (String num : scores) {
                        scoreArray.add(Integer.parseInt(num)); // what if we don't have a score in number format?
                    }
                }

                // create new golfer
                Golfer newGolfer = new Golfer(fields[0], scoreArray);

                list.add(newGolfer); // add to list

                line = emailsIn.readLine(); // read next line
            }
        }
        catch (FileNotFoundException e) {
            throw new GolfersException("File does not exist");
        }
        catch (IOException e) {
            throw new GolfersException("Error reading file");
        }
    }

    /**
     * This prints out the golfer stats
     * @param list the ArrayList of data to loop through
     * @param label true if you want numbers printed by the golfer, false if not
     */
    private static void printGolfers(ArrayList<Golfer> list, boolean label) {
        System.out.println("\n *** Golfers in System *** ");

        int count = 1;
        for (Golfer g : list) {
            if (label) {
                System.out.print(count + ". ");
                count++;
            }
            System.out.println(g + "\n");
        }
        System.out.println();
    }

    /**
     * This will add a golfer into the current list.
     * @param list the ArrayList of golfer data
     * @param in Scanner object to get input
     */
    private static void addGolfer(ArrayList<Golfer> list, Scanner in) {
        // get new person
        System.out.print("\nEnter name: ");
        String name = in.nextLine();

        // read in scores until no more
        ArrayList<Integer> scores = new ArrayList<Integer>();
        System.out.println("Enter scores: ");
        while(true) {
            String sc = in.nextLine();
            if (!sc.equals("")) { // check the input not blank
                scores.add(Integer.parseInt(sc));
            }
            else {
                break;
            }
        }

        // new golfer and add to list
        Golfer newGolfer = new Golfer(name, scores);
        if (!list.contains(newGolfer)) { // check not in the list, if so add it
            list.add(newGolfer);
        }
        else {
            System.out.println("Golfer already in the System.\n");
        }

    }

    /**
     * This will print out the golfers and have the user enter in a number to delete
     *
     * @param list the Arraylist of golfer data
     * @param input Scanner object to get input
     */
    private static void deleteGolfer(ArrayList<Golfer> list, Scanner input) {
        // list golfers with number
        printGolfers(list, true);
        // enter number to delete
        int delIndex = getInt("Enter golfer to delete: ", input);
        while (delIndex < 1 || delIndex > list.size()) { // make sure in range
            System.out.println("Not a valid golfer to delete");
            delIndex = getInt("Enter golfer to delete: ", input);
        }

        list.remove(delIndex-1); // delete index off by 1 due to printout
        System.out.println(); // space output

    }

    /**
     * This will save out the list of golfers to the file updating it.
     *
     * @param list the ArrayList of golfer data to save out
     * @throws
     */
    private static void writeOutGolfers(ArrayList<Golfer> list) throws GolfersException {
        try (PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("GolfStats.txt")))) {
            // loop through and add each line with comma in between
            for (Golfer user : list) {
                outFile.print(user.getName()+ "-"); // print name with tab
                for (int score : user.getScores()) {
                    outFile.print(score + ","); // put scores out separated by a comma
                }
                outFile.println(); // go to new line
            }
        }
        catch(IOException e) {
            throw new GolfersException("Error writing to file");
        }
    }

    /**
     * A method used to get a proper int and not throw any exceptions
     * @param prompt String value for the prompt to print
     * @param in Scanner object to get input
     * @return a valid int number
     */
    private static int getInt(String prompt, Scanner in) {
        int value;
        while (true)
        {
            System.out.print(prompt);
            if (in.hasNextInt()){
                value = in.nextInt();
                in.nextLine(); // clear scanner
                return value;
            }
            else {
                in.nextLine(); // clear scanner
            }
        }
    }
}

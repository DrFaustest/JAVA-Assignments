import java.text.NumberFormat;
import java.util.ArrayList;
/**
 * This class holds the name and an array of scores that the golfer has.
 *
 * @author Lucas Hartman
 * @version 1.0.0 - 02/15/23
 */
public class Golfer {
    private String name;
    private ArrayList<Integer> scores;

    /**
     * NumberFormatter variable to round to 2 decimals for the golfer average.
     */
    private static NumberFormat twoDecimal = NumberFormat.getNumberInstance();

    /**
     * Main constructor that takes on the name and scores. It also sets the decimal digits to 2.
     * @param name a String value for the name of the golfer
     * @param scores an int array of scores the golfer has
     */
    public Golfer(String name, ArrayList<Integer> scores) {
        this.name = name;
        this.scores = scores;
        twoDecimal.setMaximumFractionDigits(2);
    }

    /**
     * Constructor that just has the name of the golfer and no scores.
     * @param name
     */
    public Golfer(String name) {
        this(name, new ArrayList<Integer>());
    }

    /**
     * Default constructor creating an empty golfer.
     */
    public Golfer() {
        this("", new ArrayList<Integer>());
    }

    /**
     * Returns the name of the golfer back.
     * @return a String value
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of the golfer
     * @param name a String value for the name to update
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns back an int array of the scores
     * @return an int[] of golfer scores
     */
    public ArrayList<Integer> getScores() {
        return scores;
    }

    /**
     * Updates the golfers scores.
     * @param scores an int[] of scores to update
     */
    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    /**
     * Loops through the score array, adds up the scores and calculates the average
     * @return a double value of the golfer's average score.
     * @throws GolfersException
     */
    public int getAverage() {
        int sum = 0;
        if (scores.size() == 0) {
            return 0;
        }
        
        for (int num : scores) {
                    
            sum += num;
        }

        return sum / scores.size(); // what happens if length = 0...bad error!!
    }

    /**
     * Compares between two golfers using their names.
     * @param obj2 a second Golfer object to compare
     * @return true if the names match, false otherwise
     * 
     */
    @Override
    public boolean equals(Object obj2) {
        if (obj2 != null && obj2.getClass() == this.getClass()) {
            Golfer g2 = (Golfer) obj2;
            return name.equalsIgnoreCase(g2.getName()); // compare names
        }
        return false; // false otherwise
    }

    /**
     * Returns a formatted output of the golfer
     * @return a String value of the name, scores and average for the golfer
     */
    @Override
    public String toString() {
        String scores = "";
        for (int sc : this.scores) {
            scores += sc + "  ";
        }
        // ****** Arithmetic Exception happens here **********
        return "Name: " + name + "\nScores: " + scores + "\nAverage: " + getAverage();
    }
}

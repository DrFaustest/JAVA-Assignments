/**
 * This is the base movie class that holds the title, year and rating for a movie
 *
 * @author Lucas Hartman
 * @version 1.0.0 - 03/24/23
 *
 */
public class Movie {
    private String title;
    private int year;
    private double rating;

    /**
     * Main constructor that sets up a movie object.
     * @param title String for the title of the movie
     * @param year int number for the year the movie was released
     * @param rating double for the rating of them movie
     */
    public Movie (String title, int year, double rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    /**
     * Gets the title of the movie
     * @return a String for the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Updates the title of the movie
     * @param title a String for the title to update
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the year the movie was released
     * @return an int for the movie year
     */
    public int getYear() {
        return year;
    }

    /**
     * Updates the year the movie was released
     * @param year an int for the year of the movie
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the rating of the moview
     * @return a double for the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Updates the rating of the movie
     * @param rating a double for the new rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * String format of the movie object
     * @return a String for the movie
     */
    public String toString() {
        return title + " (" + year + ") Rating: " + rating;
    }

    /**
     * Used for getting a string output to write to a text file.
     * @return a String format with tabs inbetween properties
     */
    public String fileWriteOut() {
        return title + "\t" + year + "\t" + rating;
    }

    /**
     * Compares against the title and year to see if the same movie
     * @param obj a movie object to compare to
     * @return true if the movies match, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Movie) {
            Movie m2 = (Movie)obj;
            if (title.equalsIgnoreCase(m2.getTitle()) && year == m2.getYear()) {
                return true;
            }
        }
        return false;
    }
}

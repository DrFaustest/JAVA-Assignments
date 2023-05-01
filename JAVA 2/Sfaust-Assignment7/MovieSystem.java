/*
 * Name:        Scott Faust
 * Class:       22_SP_INFO_1531_WW
 * Assignment:  MovieCollection.java
 * Date:        4/25/2023
 * Resources:   Lectures and examples 
 * 
 * Description: This program is a movie collection that allows the user to add movies, view top rated movies, view most recent movies, view all movies, and view ratings.
 */

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.List;


/**
 * The running file that provides options to add, see all, see top rated, more recent
 * and complete stats of movies
 *
 * @Author Lucas Hartman with modifications by YOURNAME
 * @version 1.0.0 - 3/27/2023
 *
 * YOUR TO-DO
 *  Complete the viewTopMovies and viewRecentMovies to use the Predicate function
 *
 *  Complete the viewRatings() to call methods for ratings on the MovieCollection
 *
 */
public class MovieSystem {
    /**
     * Runner method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        MovieCollection db = new MovieCollection();
        Scanner input = new Scanner(System.in);

        int operation = 0;
        while (operation != 6) {
            System.out.println("Welcome to the Movie Ratings application");
            System.out.println("1. Enter a Movie\n2. View Top Rated Movies\n" +
                    "3. View Most Recent Movies\n4. View All Movies\n5. View Ratings\n6. Quit Application");
            operation = getInt("Choose menu option: ", 1, 6, input);

            switch (operation) {
                case 1 -> enterMovie(db, input);
                case 2 -> viewTopMovies(db, input);
                case 3 -> viewRecentMovies(db, input);
                case 4 -> db.printAllMovies();
                case 5 -> viewRatings(db);
                case 6 -> {
                    db.writeOutMovies();
                    System.out.println("System exiting, movies saved out.");
                }
            }
        }

    }

    /**
     * Add a new movie into the system
     * @param db MovieCollection object to add movie to
     * @param sc Scanner object for input
     */
    public static void enterMovie(MovieCollection db, Scanner sc) {
        int movieCount = getInt("How many movies do you want to enter? ", sc);
        for (int i = 0; i < movieCount; i++) {
            System.out.println("\n** Movie #" + (i+1) + " **\n----------");
            System.out.print("Enter title: ");
            String title = sc.nextLine();
            int year = getInt("Enter year: ", sc);
            double rating = getRating("Enter rating between 1 and 5(decimals OK): ", sc);

            // create and add new movie to the list
            Movie newMovie = new Movie(title, year, rating);
            db.addMovie(newMovie);
        }
        System.out.println();
    }

    /**
     * This method views movies with rating of 4.0. Filter with lambda expressions
     * @param db the MovieCollection object
     *
     *
     */
    public static void viewTopMovies(MovieCollection db, Scanner scanner) {
    System.out.println("Enter the minimum rating for top movies (1 to 5):");
    double minRating = scanner.nextDouble();

    // Input validation
    if (minRating < 1 || minRating > 5) {
        System.out.println("Invalid input. Please enter a rating between 1 and 5.");
        return;
    }

    Predicate<Movie> topRatedFilter = m -> m.getRating() >= minRating;
    List<Movie> topRatedMovies = db.filterMovies(topRatedFilter);
    topRatedMovies.forEach(System.out::println);
}


    /**
     * This method views movies made within the last 5 years. Filter with lambda expressions
     *
     * @param db the MovieCollection object to use for filtering
     * 
     * The oldest surviving film is Roundhay Garden Scene from the year 1888 and at 2.5 seconds long its arguably too short to be given a rating
     * Aditionally, this function is designed to show "recent" movies, and as such, anything over 100 years old is hardly recent. 
     *
     */
    public static void viewRecentMovies(MovieCollection db, Scanner scanner) {
        System.out.println("Enter the starting year for recent movies (1900 to current year):");
        int startYear = scanner.nextInt();
    
        int currentYear = java.time.Year.now().getValue();
    
        // Input validation
        if (startYear < 1900 || startYear > currentYear) {
            System.out.println("Invalid input. Please enter a year between 1900 and the current year.");
            return;
        }
    
        Predicate<Movie> recentMoviesFilter = m -> m.getYear() >= startYear;
        List<Movie> recentMovies = db.filterMovies(recentMoviesFilter);
        recentMovies.forEach(System.out::println);
    }

    /**
     * This method will print out movie rating data including movie count, lowest rating,
     * highest rating, and average rating.
     * @param db MovieCollection object to use for filtering
     *
     * 
     */
    public static void viewRatings(MovieCollection db) {
        System.out.println("\n** Movie Ratings Data **");
        System.out.println("Movie Count: " + db.movieCount());
        System.out.printf("Lowest Rating: %.2f\n", db.getLowestRating());
        System.out.printf("Highest Rating: %.2f\n", db.getHighestRating());
        System.out.printf("Average Rating: %.2f\n\n", db.getAverageRating());
    }

    /**
     * Helper method that gets an int within a range
     * @param prompt String to print out for prompting the user
     * @param low value the number can't be below
     * @param high value the number can't be above
     * @param sc Scanner object to get input
     * @return an int within the range of the low and high
     */
    private static int getInt(String prompt, int low, int high, Scanner sc) {
        int number = getInt(prompt, sc);
        while(number < low || number > high) {
            System.out.println("Not a valid option.");
            number = getInt(prompt, sc);
        }
        return number;
    }

    /**
     * Helper method to get valid integer for scanner input
     * @param prompt String to print out for prompting the user
     * @param sc Scanner object to get input
     * @return an int from keyboard input
     */
    private static int getInt(String prompt, Scanner sc) {
        while(true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                return Integer.parseInt(line);
            }
            catch (NumberFormatException e){
                System.out.println("Error, not a valid number.");
            }
        }
    }

    /**
     * Helper method that gets a valid double valid for a rating of 1-5.
     * @param prompt String value to print for input statement
     * @param sc Scanner object for getting input
     * @return a valid double rating value 1-5
     */
    private static double getRating(String prompt, Scanner sc) {
        while(true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                double rating = Double.parseDouble(line);
                if (rating < 1 || rating > 5) {
                    System.out.println("Error, rating must be between 1 and 5.");
                    continue;
                }
                return rating;
            }
            catch (NumberFormatException e){
                System.out.println("Error, not a valid number.");
            }
        }
    }
}

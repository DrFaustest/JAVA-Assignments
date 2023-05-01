/*
 * Name:        Scott Faust
 * Class:       22_SP_INFO_1531_WW
 * Assignment:  MovieCollection.java
 * Date:        4/25/2023
 * Resources:   Lectures and examples 
 * 
 * Description: This program is a movie collection that allows the user to add movies, view top rated movies, view most recent movies, view all movies, and view ratings.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This is the data base file that holds on the movies. Reads in and writes out the data
 * from the movies.txt file. It also does the processing for the different filtering/data
 * collection of the movies list.
 *
 * @author Lucas Hartman with modifications by YOURNAME
 * @version 1.0.0 - 03/27/2023
 *
 * YOUR TO-DO
 *
 * Create the filterMovies() method here that uses predicate
 *
 * Create the getLowestRating, getHighestRating and getAverageRating() methods here.
 * All these methods will return a double value back and use stream map and reduce methods
 *
 */
public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();

    /**
     * main constructor that sets up the db by reading in the movies
     */
    public MovieCollection() {
        readInMovies();
    }

    /**
     * Gets the number of movies in the system.
     * @return an int for number of movies.
     */
    public int movieCount() {
        return movies.size();
    }

    /**
     * Prints out all the movies in the list.
     */
    public void printAllMovies() {
        System.out.println("\n** All Movies in System **");
        for (Movie m : movies) {
            System.out.println(m);
        }
        System.out.println();
    }


    /**
     * Reads in the movies from the movies.txt file and puts them in to the list
     */
    public void readInMovies() {
        try(BufferedReader reader = new BufferedReader(new FileReader("movies.txt"))) {
            reader.readLine(); // skip the header
            String line = reader.readLine();
            while (line != null) {
                // break up the data
                String[] data = line.split("\t");

                // create movie and add to list
                Movie newMovie = new Movie(data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2]));
                movies.add(newMovie);

                // read next line
                line = reader.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File doesn't exist. One will get created upon exiting");
        }
        catch (IOException e) {
            System.out.println("Error reading the file.");
        }

    }

    /**
     * writes out the movies to the text file to update
     */
    public void writeOutMovies() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("movies.txt"))) {
            writer.println("title\tyear\trating");

            // call writeout method on the movie object
            for (Movie m : movies) {
                writer.println(m.fileWriteOut() + "\t");
            }

        }
        catch (IOException e) {
            System.out.println("Problem writing out to file.");
        }
    }

    /**
     * This will add a new movie into the list. It checks to make sure the movie isn't already in there.
     * @param movie a new movie object to add
     */
    public void addMovie(Movie movie) {
        if (!movies.contains(movie)) {
            movies.add(movie);
        }
    }
    
    /** 
     * 
     * @param filter
     * @return List<Movie>
     */
    public List<Movie> filterMovies(Predicate<Movie> filter) {
        return movies.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    /**
     * Gets the lowest rating of the movies in the list
     * @return a double for the lowest rating
     */
    public double getLowestRating() {
        return movies.stream()
                .mapToDouble(Movie::getRating)
                .min()
                .orElse(Double.NaN);
    }
    /**
     * Gets the highest rating of the movies in the list
     * @return  double for the highest rating
     */

    public double getHighestRating() {
        return movies.stream()
                .mapToDouble(Movie::getRating)
                .max()
                .orElse(Double.NaN);
    }

    /**
     * Gets the average rating of the movies in the list
     * @return double for the average rating
     */
    public double getAverageRating() {
        OptionalDouble average = movies.stream()
                .mapToDouble(Movie::getRating)
                .average();
        return average.isPresent() ? average.getAsDouble() : Double.NaN;
    }
}

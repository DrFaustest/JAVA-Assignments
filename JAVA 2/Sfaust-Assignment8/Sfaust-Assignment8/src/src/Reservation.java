/**
 * 
 * @author Scott Faust
 * @version 1.0
 * 
 * Class:       22_SP_INFO_1531_WW
 * Assignment:  8 - Hotel Reservation System
 * Date:        5/1/2023
 * Resources:   Lectures and examples 
 * 
 * Description: This class is used to create a reservation object
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Reservation
 */
public class Reservation {
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private final double NIGHTLY_RATE = 145.00;

    /**
     * Default constructor
     */
    public Reservation() {
        arrivalDate = LocalDate.now();
        departureDate = LocalDate.now();
    }
    
    /**
     * Constructor
     * 
     * @param arrivalDate
     * @param departureDate
     */
    public Reservation(LocalDate arrivalDate, LocalDate departureDate) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }
    
    
    /** Returns the arrivalDate
     * 
     * @return the arrivalDate
     */
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    
    /**Returns the arrivalDate formatted as a string
     * Full day of week, full month, digit day of month, 4 digit year
     * 
     * @return the arrivalDate formatted
     */
    public String getArrivalDateFormatted() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        return dtf.format(arrivalDate);
    }
    
    /** Changes the arrivalDate to the date entered
     * 
     * @param arrivalDate the arrivalDate to set
     */
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    
    /** Returns the departureDate
     * 
     * @return the departureDate
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    
    /** Returns the departureDate formatted as a string
     * Full day of week, full month, digit day of month, 4 digit year
     * 
     * @return the departureDate formatted
     */
    public String getDepartureDateFormatted() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        return dtf.format(departureDate);
    }
    
    /** Changes the departureDate to the date entered
     * 
     * @param departureDate the departureDate to set
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    
    /** Calculates the number of nights between the arrivalDate and departureDate
     * Takes into account months and years as well (had an issue with this only calculating days at first)
     * 
     * @return the number of nights
     */
    public int getNumberOfNights() {
        return (int) (departureDate.toEpochDay() - arrivalDate.toEpochDay());
    }
    
    /** Formats the price per night
     * 
     * @return the nightly rate
     */
    public String getPricePerNightFormatted() {
        return String.format("$%.2f", NIGHTLY_RATE);
    }
    
    /** Calculates the total price of the reservation
     * 
     * @return the total price
     */
    public double getTotalPrice() {
        return NIGHTLY_RATE * getNumberOfNights();
    }
    
    /** Formats the total price of the reservation
     * 
     * @return the total price formatted
     */
    public String getTotalPriceFormatted() {
        return String.format("$%.2f", getTotalPrice());
    }
}
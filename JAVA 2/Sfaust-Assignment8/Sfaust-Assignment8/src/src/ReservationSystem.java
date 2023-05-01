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
 * Description: The ReservationSystem is the main class for the Hotel Reservation System and is used to create a reservation object and interact with the user
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReservationSystem {
    
    /** 
     * @param args
     * 
     * Main method
     */
    public static void main(String[] args) {
        System.out.println("**Reservation Calculator**");
        System.out.println();
        Scanner sc = new Scanner(System.in);

        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
            Reservation reservation = initializeReservation(sc);
            confirmReservation(reservation, sc);
            System.out.print("Continue? (y/n): ");
            choice = yesNoValidation(sc);
            System.out.println();
        }
        System.out.println("**Reservation System Exit**");
    }

    /**
     * Initialize the reservation object
     * 
     * @param sc
     * 
     */
    public static Reservation initializeReservation(Scanner sc) {
        LocalDate arrivalDate = validateEnteredDate(sc, "arrival");
        LocalDate departureDate = validateEnteredDate(sc, "departure");
        Reservation reservation = new Reservation(arrivalDate, departureDate);
        return reservation;
    }
    /**
     *  Validate that the date entered is in the correct format
     * If not display an error message and send to the initializeReservation method
     * Otherwise return the date
     * catches DateTimeParseException if the date is not in the correct format
     * 
     * @param sc
     * @param prompt
     * @return
     */

    public static LocalDate validateEnteredDate(Scanner sc, String prompt) {
        System.out.print("Enter " + prompt + " date (mm/dd/yyyy): ");
        String uncheckedDate = sc.next();
        System.out.println();
        String[] dateArray = uncheckedDate.split("/");
        if (dateArray.length != 3 || !dateArray[0].matches("\\d{1,2}") || !dateArray[1].matches("\\d{1,2}") || !dateArray[2].matches("\\d{4}")) {
            System.out.println("Error: date entered incorrectly.");
            System.out.println();
            return validateEnteredDate(sc, prompt);
        } else {
            try {
                // convert the date to a LocalDate object
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate date = LocalDate.parse(uncheckedDate, dtf);
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Error: date entered incorrectly.");
                System.out.println();
                return validateEnteredDate(sc, prompt);
            }
        }
    }
    
    
    /**
    *   Validate that the arrival date is before the departure date and after the current date
    *   If not display an error message and send to the editReservationDates method
    *   Otherwise display the reservation details and ask if they want to confirm the reservation or make changes
    *   
    *   @param reservation
    */
    public static void confirmReservation(Reservation reservation, Scanner sc){
        if (reservation.getArrivalDate().isAfter(reservation.getDepartureDate())) {
            System.out.println("Error: departure date must be after arrival date.");
            System.out.println();
            editReservationDates(reservation, sc);
        } else if (reservation.getArrivalDate().isBefore(LocalDate.now())) {
            System.out.println("Error: arrival date must be after today's date.");
            System.out.println();
            editReservationDates(reservation, sc);
        } else {
            displayReservation(reservation);
            System.out.print("Confirm reservation? (y/n): ");
            String choice = yesNoValidation(sc);
            System.out.println();
            if (choice.equalsIgnoreCase("y")) {
                System.out.println("**Reservation Confirmed**");
                System.out.println();
            } else {
                editReservationDates(reservation, sc);
            }
        }

    }

    
    /** 
     * @param reservation
     * 
     * Display the reservation details
     */

    public static void displayReservation(Reservation reservation){
            System.out.println("Arrival Date: " + reservation.getArrivalDateFormatted());
            System.out.println("Departure Date: " + reservation.getDepartureDateFormatted());
            System.out.println("Price: " + reservation.getPricePerNightFormatted() + " per night.");
            System.out.println("Total Price: " + reservation.getTotalPriceFormatted() + " for " + reservation.getNumberOfNights() + " night(s).");
    }

    
    /** 
    * @param reservation
    * @param sc
    *
    *  Edit the reservation dates 
    *   Display the current arrival and departure dates and prompt for changes 
    *   Use a switch statement to determine which date to change 1 arival 2 departure and 3 both
    *
    */
    public static void editReservationDates(Reservation reservation, Scanner sc){
        System.out.println("Current Arrival Date: " + reservation.getArrivalDateFormatted() + "("+ reservation.getArrivalDate() + ")");
        System.out.println("Current Departure Date: " + reservation.getDepartureDateFormatted() + "("+ reservation.getDepartureDate() + ")");
        System.out.println();
        System.out.println("Would you like to change the arrival date, departure date, or both?");
        System.out.println("1. Arrival Date");
        System.out.println("2. Departure Date");
        System.out.println("3. Both");
        System.out.println("4. Exit Reservation System");
        System.out.println();
        System.out.print("Enter option: ");
        int choice = tryIntigerInputValidation(sc);
        System.out.println();
        switch (choice) {
            case 1:
                reservation.setArrivalDate(validateEnteredDate(sc, "arrival"));
                break;
            case 2:
                reservation.setDepartureDate(validateEnteredDate(sc, "departure"));
                break;
            case 3:
                reservation.setArrivalDate(validateEnteredDate(sc, "arrival"));
                reservation.setDepartureDate(validateEnteredDate(sc, "departure"));
                break;
            case 4:
                System.out.println("**Reservation System Exit**");
                System.exit(0);
                break;
            default:
                System.out.println("Error: invalid option.");
                System.out.println();
                editReservationDates(reservation, sc);
        }
        confirmReservation(reservation, sc);
    }

    static Integer tryIntigerInputValidation(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Error: invalid input. Must be a whole number.");
            System.out.println();
            sc.next();
        }
        return sc.nextInt();
    }
    static String yesNoValidation(Scanner sc){
        String choice = sc.next();
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            System.out.println("Error: invalid input. Must be 'y' or 'n'.");
            System.out.println();
            choice = sc.next();
        }
        return choice.toLowerCase();
    }
}

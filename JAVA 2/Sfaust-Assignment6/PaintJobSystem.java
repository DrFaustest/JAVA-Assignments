import java.util.Scanner;

/**
 * This is the Paint Job Invoice System. It provides the functionality to see
 * the next job, see the current jobs
 * that the 3 teams the company has are working on, assign jobs to those teams,
 * mark jobs as completed and add new jobs
 * to the queue.
 *
 * YOUR TO-DO (You can delete these once complete)
 * Complete the viewNextJob() method. This should use the queue's .peek() method
 * and print out what the next job.
 * You should also make sure that the queue isn't empty and hasNextJob(). It
 * shouldn't print out 'null'
 *
 * Complete the viewTeamJobs() method. This should also use the .peek() method
 * to print out the current job for
 * each Team. If a team doesn't have a job it shouldn't print out 'null' but
 * instead '*None*'. You should also
 * print headers like *** Team 1*** before list the job so we know what team has
 * what job.
 *
 * Complete the assignJob() method. The menu and input is complete you just need
 * to complete the switch part.
 * case 1 is for team 1, etc. So you need to go through and dequeue the job from
 * the invoice and enqueue it into
 * whichever team queue we are dealing with. BUT you shouldn't add a job to the
 * team IF they are already working
 * on a job. So check if the queue is empty and if so, assign the job.
 *
 * Complete the addNewJob() method. This should take on a bunch of inputs to
 * create a new Invoice. Make sure to
 * do some data validation/checks(there is also a getDouble() method that you
 * can call on). Once you get all the
 * inputs, create an invoice and add it to the invoice queue on the db object.
 * We can assume this job we added
 * is AFTER all the invoices BUT to be safe we should also sort the invoices
 * queue again to make sure.
 *
 * Complete the exit() method. This method should take out whatever the current
 * jobs that team1, team2, and team3
 * are currently working on (since they aren't complete upon exit) and add them
 * back into the invoice queue on the
 * db object. Then call the write out methods for invoices and completed on the
 * PaintJobDB.
 *
 * NOTES/THINGS TO CATCH:
 * The IDE may auto import at the top the Java built in Queue. If this happens
 * delete the import statement.
 * Make sure it uses the Queue.java file that you created for this assignment. A
 * big red flag on this will
 * be that Java's Queue is an interface and won't allow you to create an object
 * with it, so if you see that
 * error, check the imports above.
 *
 *
 *
 * @author Lucas Hartman
 * @version 1.0.0 - 3/21/23
 *
 */
public class PaintJobSystem {
    public static void main(String[] args) {

        Queue<Invoice> team1 = new Queue<>();
        Queue<Invoice> team2 = new Queue<>();
        Queue<Invoice> team3 = new Queue<>();

        PaintJobDB database = new PaintJobDB();
        Scanner input = new Scanner(System.in);
        int operation = 0;
        while (operation != 6) {
            System.out.println("*** Happy Accidents Paint Job Invoices ***");
            System.out.println("1. View Next Job"); // within this assign to team 1-3
            System.out.println("2. See Team's Current Jobs");
            System.out.println("3. Assign Job to Team");
            System.out.println("4. Complete Job"); // pick a team (1-3) and add to done stack
            System.out.println("5. Add New Job");
            System.out.println("6. Exit"); // write out all files

            // get operation to do
            operation = getInt("Operation: ", input);
            while (operation < 1 || operation > 6) {
                System.out.println("Error, not a valid operation.");
                operation = getInt("Operation:", input);
            }
            System.out.println();

            switch (operation) {
                case 1 -> viewNextJob(database);
                case 2 -> viewTeamJobs(database, team1, team2, team3);
                case 3 -> {
                    if (database.invoices != null) {
                        assignJob(database, team1, team2, team3, input);
                    } else {
                        System.out.println("No jobs in queue.");
                    }
                }
                case 4 -> completeJob(database, team1, team2, team3, input);
                case 5 -> addNewJob(database, input);
                case 6 -> exit(database, team1, team2, team3);
            }

        }
    }

    /**
     * This method will look at the next job in the invoices queue on the db object
     *
     * YOUR TO-DO (Passed)
     *
     * @param db the PaintJobDB object storing all the job data
     */
    public static void viewNextJob(PaintJobDB db) {
        System.out.println("*** Next Job ***");
        if (db.invoices.peek() != null) {
            System.out.println(db.invoices.peek());
        } else {
            System.out.println("No jobs in queue.");
        }

    }

    /**
     * This method will print out a header for each team and their current job. If
     * there is no job they
     * are working on, '*NONE*' should be printed. There should be no 'null' printed
     * here.
     *
     * YOUR TO-DO (Passed)
     *
     * @param db    The PaintJobDB object storing all the job data
     * @param team1 the queue for team1
     * @param team2 the queue for team2
     * @param team3 the queue for team3
     */
    public static void viewTeamJobs(PaintJobDB db, Queue<Invoice> team1, Queue<Invoice> team2, Queue<Invoice> team3) {
        System.out.println("*** Team Jobs ***");
        System.out.println("*** Team 1 ***");
        if (team1.peek() != null) {
            System.out.println(team1.peek());
        } else {
            System.out.println("*NONE*");
        }
        System.out.println("*** Team 2 ***");
        if (team2.peek() != null) {
            System.out.println(team2.peek());
        } else {
            System.out.println("*NONE*");
        }
        System.out.println("*** Team 3 ***");
        if (team3.peek() != null) {
            System.out.println(team3.peek());
        } else {
            System.out.println("*NONE*");
        }

    }

    /**
     * This method will assign new a new paint job to a chosen team. Each team
     * should only have one job at a time.
     *
     * YOUR TO-DO - (passed)
     *
     * @param db    The PaintJobDB object storing all the job data
     * @param team1 the queue for team1
     * @param team2 the queue for team2
     * @param team3 the queue for team3
     * @param sc    the Scanner object for getting input
     */
    public static void assignJob(PaintJobDB db, Queue<Invoice> team1, Queue<Invoice> team2,
            Queue<Invoice> team3, Scanner sc) {
        System.out.println("*** Assign Job ***");
        System.out.println("1. Team 1");
        System.out.println("2. Team 2");
        System.out.println("3. Team 3");
        int team = getInt("Choose: ", sc);
        while (team < 1 || team > 3) {
            System.out.println("Not a valid team");
            team = getInt("Choose: ", sc);
        }

        // COMPLETE THIS
        switch (team) {
            case 1 -> team1.enqueue(db.invoices.dequeue());
            case 2 -> team2.enqueue(db.invoices.dequeue());
            case 3 -> team3.enqueue(db.invoices.dequeue());

        }
        System.out.println(); // space out
    }

    /**
     * This method will have the user enter in a team number that has completed
     * their job. If the team doesn't
     * have a job then an error message will appear. Once a job is complete, it will
     * ask to assign a new one or not.
     *
     * @param db    The PaintJobDB object storing all the job data
     * @param team1 the queue for team1
     * @param team2 the queue for team2
     * @param team3 the queue for team3
     * @param sc    the Scanner object for getting input
     */
    public static void completeJob(PaintJobDB db, Queue<Invoice> team1, Queue<Invoice> team2,
            Queue<Invoice> team3, Scanner sc) {
        System.out.println("\n*** Complete Jobs ***");
        System.out.println("1. Team 1");
        System.out.println("2. Team 2");
        System.out.println("3. Team 3");
        int team = getInt("Choose: ", sc);
        while (team < 1 || team > 3) {
            System.out.println("Not a valid team");
            team = getInt("Choose: ", sc);
        }
        Queue<Invoice> currentTeam = null;
        switch (team) {
            case 1 -> currentTeam = team1;

            case 2 -> currentTeam = team2;

            case 3 -> currentTeam = team3;
        }

        if (!currentTeam.isEmpty()) {
            db.completedJob(currentTeam.dequeue());

            if (db.hasNextJob()) {
                System.out.println("\nAssign next invoice?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int choice = getInt("Choice: ", sc);
                while (choice < 1 || choice > 2) {
                    System.out.println("Not a valid choice");
                    choice = getInt("Choice: ", sc);
                }
                if (choice == 1) {
                    currentTeam.enqueue(db.assignNextJob());
                }
            } else {
                System.out.println("\nJob queue is empty.");
            }
        } else {
            System.out.println("\nError, team doesn't have a job to complete.");
        }
        System.out.println(); // blank space
    }

    /**
     * This method will get input for all the data properties of an invoice, create
     * a new invoice and add to the
     * invoice queue on the database object. This will also do a date sort on the
     * queue.
     *
     * YOUR TO-DO - make sure to do some data validation here as well.
     *
     * @param db The PaintJobDB object storing all the job data
     * @param sc the Scanner object for getting input
     */
    public static void addNewJob(PaintJobDB db, Scanner sc) {
        // this method gets inputs for each property of an invoice
        // int id, string date, string last name, string first name, string
        // address(validate int for number and zip code, abbrievate state to 2 capital
        // letters, city and street name), string paint brand, use paintfinish.java to
        // select paint finish, string color and int double cost.
        // use the scanner sent in the parameter sc to get input for each property
        // create a new invoice object and add it to the queue from the db object

        System.out.println("*** Add New Job ***");
        System.out.println("Enter the following information for the new job:");
        String id = getString("ID: ", sc);
        String date = getString("Date: ", sc);
        String lastName = getString("Last Name: ", sc);
        String firstName = getString("First Name: ", sc);
        String address = getString("Address: ", sc);
        String paintBrand = getString("Paint Brand: ", sc);
        PaintFinish paintFinish = getPaintFinish("Paint Finish: ", sc);
        String color = getString("Color: ", sc);
        double cost = getDouble("Cost: ", sc);

        Invoice newInvoice = new Invoice(id, date, lastName, firstName, address, paintBrand, paintFinish, color, cost);
        db.invoices.enqueue(newInvoice);

        System.out.println(); // blank space
    }

    /**
     * This method gets called when exiting. It will loop through team1, team2, and
     * team3 queues adding those
     * invoice objects back into the invoice queue on the db object. This saves
     * those since a team might not have
     * them complete. It then calls the write out methods for invoices and completed
     * to save the files.
     *
     * YOUR TO-DO
     *
     * @param db    The PaintJobDB object storing all the job data
     * @param team1 the queue for team1
     * @param team2 the queue for team2
     * @param team3 the queue for team3
     */
    public static void exit(PaintJobDB db, Queue<Invoice> team1, Queue<Invoice> team2, Queue<Invoice> team3) {
        System.out.print("Exiting...");

        // dequeue team1 and add back to db invoices
        while (!team1.isEmpty()) {
            db.addToInvoices(team1.dequeue());
        }

        // dequeue team2 and add back to db invoices
        while (!team2.isEmpty()) {
            db.addToInvoices(team2.dequeue());
        }

        // dequeue team3 and add back to db invoices
        while (!team3.isEmpty()) {
            db.addToInvoices(team3.dequeue());
        }

        // write out invoices
        db.writeOutInvoices();

        // write out completed invoices
        db.writeOutCompleted();

        System.out.println("Invoices Saved Out.");
    }

    /**
     * Helper method that gets a valid integer number from keyboard input
     *
     * @param prompt a String value to print out for the prompt of the user
     * @param sc     the Scanner to get input
     * @return a valid int number
     */
    private static int getInt(String prompt, Scanner sc) {
        while (true) {
            System.out.print(prompt);

            String input = sc.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error, enter in a number.");
            }
        }
    }

    /**
     * Helper method that gets a valid double number from the keyboard.
     *
     * @param prompt a String value to print out for the prompt of the user
     * @param sc     the Scanner to get input
     * @return a valid double number
     */
    private static double getDouble(String prompt, Scanner sc) {
        while (true) {
            System.out.print(prompt);

            String input = sc.nextLine();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error, enter in a number.");
            }
        }
    }

    private static String getString(String prompt, Scanner sc) {
        Boolean validInput = false;
        String input = "";
        switch (prompt) {
            case "ID: ":
                do {
                    System.out.print(prompt);
                    input = sc.nextLine();
                    if (input.matches("^[a-zA-Z0-9]+$")) {
                        validInput = true;
                    } else {
                        System.out.println("Error, enter in a valid ID.\n Format: XXXX\neg. 1234");
                    }
                } while (!validInput);
                break;
            case "Date: ":
                validInput = false;
                do {
                    System.out.print(prompt);
                    input = sc.nextLine();
                    if (input.matches("^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$")) {
                        // be sure to remove / and replace with - for the date format before returning
                        if (input.contains("/")) {
                            input = input.replace("/", "-");
                        }
                        validInput = true;
                    } else {
                        System.out.println("Error, enter in a valid date.\n Format: MM-DD-YYYY\neg. 01-01-2021");
                    }
                } while (!validInput);
                break;
            case "Last Name: ":
            case "First Name: ":
            case "Address: ":
            case "Paint Brand: ":
            case "Color: ":
                validInput = false;
                do {
                    System.out.print(prompt);
                    input = sc.nextLine();
                    if (input.matches("^[a-zA-Z0-9]+(?:[\s-][a-zA-Z0-9]+)*$")) {
                        validInput = true;
                    } else {
                        System.out.println("Error, enter in a valid input.\n Format: Letters and numbers only");
                    }
                } while (!validInput);
                break;
            default:
                System.out.println("Error, enter in a valid input.");
                break;
        }
        return input;
    }

    private static PaintFinish getPaintFinish(String prompt, Scanner sc) {
        while (true) {
            System.out.print(prompt);
            System.out.println("Please select from the following options:");
            for (PaintFinish finish : PaintFinish.values()) {
                System.out.println(finish.ordinal() + 1 + ". " + finish);
            }
            String inputPaintFinish = sc.nextLine();
            if (inputPaintFinish.matches("^[1-" + PaintFinish.values().length + "]$")) {
                int selection = Integer.parseInt(inputPaintFinish) - 1;
                return PaintFinish.values()[selection];
            } else {
                System.out.println("Error, enter in a valid paint finish.\n Format: 1-" + PaintFinish.values().length);
            }
        }
    }
}

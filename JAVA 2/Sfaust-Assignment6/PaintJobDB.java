import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

/**
 * This is the database object that holds both the queue of invoices and the stack of completed.
 * This reads in and writes out both the queue and stack to their respective classes when the system exits.
 *
 * YOUR TO-DO: (You can delete these once complete)
 *     in the writeOutCompleted() method everything is set EXCEPT that we need the invoices to be
 *     alphabetized. Currently, they are just stacked from when they were completed. Since you should have
 *     already written the compareTo() to sort by date we need to write our own to compare lastName and firstName
 *     properties in a custom sorting. Use whatever method you would like, but they should be alphabetized from a-z.
 *
 * NOTES/THINGS TO CATCH:
 *     The IDE may auto import at the top the Java built in Queue. If this happens delete the import statement.
 *     Make sure it uses the Queue.java file that you created for this assignment. A big red flag on this will
 *     be that Java's Queue is an interface and won't allow you to create an object with it, so if you see that
 *     error, check the imports above.
 *
 * @author Lucas Hartman with modifications by YOURNAME
 * @version 1.0.0 - 3/21/23
 *
 */
public class PaintJobDB {
    Queue<Invoice> invoices = new Queue<>();
    Stack<Invoice> completeInvoices = new Stack<>(); // use a stack for stack of papers

    /**
     * The constructor reads in the invoices and completed invoices form their respective text file.
     */
    public PaintJobDB() {
        readInInvoices();
        readInCompleted();
    }

    /**
     * Returns back the queue(the one you created) of the invoices in the database.
     * @return a Queue of invoices
     */
    public Queue<Invoice> getInvoices() {
        return invoices;
    }

    /**
     * Adds an item into the queue(the one you created) of invoices.
     * @param in a new Invoice to add into the queue
     */
    public void addToInvoices(Invoice in) {
        invoices.enqueue(in);
    }

    /**
     * Checks if the invoice has a job or is empty
     * @return true if not empty, false otherwise.
     */
    public boolean hasNextJob() {
        return !invoices.isEmpty();
    }

    /**
     * Adds the job sent into the completeInvoice stack.
     * @param job the invoice for the job completed.
     */
    public void completedJob (Invoice job) {
        completeInvoices.push(job);
    }

    /**
     * Sends the next job in the invoice queue to be completed.
     * @return the top Invoice in the queue
     */
    public Invoice assignNextJob() {
        return invoices.dequeue();
    }

    /**
     * Reads in the PaintJobInvoices.txt file, sorts it, and loads into the queue.
     */
    public void readInInvoices() {
        // read in file, save to arraylist, addAll() into the queue
        ArrayList<Invoice> readInData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("PaintJobInvoices.txt"))) {
            reader.readLine(); // skip the headers
            String line = reader.readLine();
            while (line != null) {
                // break up the data
                String[] data = line.split("\t");

                // setup paint finish based on text
                PaintFinish finish = switch(data[6]) {
                    case "Eggshell" -> PaintFinish.EGGSHELL;
                    case "Satin" -> PaintFinish.SATIN;
                    case "Semi-Gloss" -> PaintFinish.SEMI_GLOSS;
                    case "Gloss" -> PaintFinish.GLOSS;
                    default -> PaintFinish.FLAT;
                };


                Invoice newInvoice = new Invoice(data[0], data[1], data[2], data[3], data[4],
                        data[5], finish, data[7], Double.parseDouble(data[8]));

                // add to ArrayList
                readInData.add(newInvoice);

                // read next line
                line = reader.readLine();
                //System.out.println(line); - for testing (Passed)
            }

            // once all data run in, sort and use addAll() on the Queue to create the job list
            //Collections.sort(readInData);
            
            // double check that all the lines in invoices are present pefore adding to the queue (Passed)
            // System.out.println(readInData);
        
            invoices.addAll(readInData);
            // check that the queue is populated with the invoices from the text file (passed)
            System.out.println(invoices.size()); //(9)
            System.out.println(invoices.peek()); //(passed)
            //System.out.println(invoices);

        }
        catch (FileNotFoundException e) {
            System.out.println("No file exists.");
        }
        catch (IOException e) {
            System.out.println("Error when reading in data.");
        }

    }

    /**
     * Saves out the invoice queue back to the PaintJobInvoices.txt file for the next time to run.
     */
    public void writeOutInvoices() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("PaintJobInvoices.txt"))) {
            writer.println("id\tdate\tlastname\tfirstname\taddress\tpaint\tpaintfinish\tcolor\tcost");

            // call the writeout method of the object
            while (!invoices.isEmpty()) {
                writer.println(invoices.dequeue().fileWriteOut());
            }
        }
        catch(IOException e) {
            System.out.println("Problem writing to file.");
        }

    }

    /**
     * This reads in the jobs that are completed from CompletedInvoices.txt file.
     * This saves it into the complete stack to be added to in the future.
     */
    public void readInCompleted() {
        // read in file, save to arraylist, addAll() into the queue
        ArrayList<Invoice> readInData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("CompletedInvoices.txt"))) {
            reader.readLine(); // skip the headers
            String line = reader.readLine();
            while (line != null) {
                // break up the data
                String[] data = line.split("\t");

                // setup paint finish based on text
                PaintFinish finish = switch(data[6]) {
                    case "Eggshell" -> PaintFinish.EGGSHELL;
                    case "Satin" -> PaintFinish.SATIN;
                    case "Semi-Gloss" -> PaintFinish.SEMI_GLOSS;
                    case "Gloss" -> PaintFinish.GLOSS;
                    default -> PaintFinish.FLAT;
                };

                // create invoice item
                Invoice newInvoice = new Invoice(data[0], data[1], data[2], data[3], data[4],
                        data[5], finish, data[7], Double.parseDouble(data[8]));

                // add to ArrayList
                readInData.add(newInvoice);

                // read next line
                line = reader.readLine();
            }

            completeInvoices.addAll(readInData); // add into the stack once sorted

        }
        catch (FileNotFoundException e) {
            System.out.println("Completed Invoices file doesn't exist. Will create one upon exit.");
        }
        catch (IOException e) {
            System.out.println("Error when reading in data.");
        }

    }

    /**
     * This will save out all the invoices that were completed to the CompletedInvoices.txt file.
     *
     * This is your TO-DO. These items BEFORE saving out need to be sorted alphabetically from A-Z
     */
    public void writeOutCompleted() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("CompletedInvoices.txt"))) {
            writer.println("id\tdate\tlastname\tfirstname\taddress\tpaint\tpaintfinish\tcolor\tcost");

            // before writing out SORT via last name and first name
            Collections.sort(completeInvoices); // this is NOT right, set up your custom comparator

            // call the writeout method of the object
            while (!completeInvoices.isEmpty()) {
                writer.println(completeInvoices.pop().fileWriteOut());
            }
        }
        catch(IOException e) {
            System.out.println("Problem writing to file.");
        }

    }
}

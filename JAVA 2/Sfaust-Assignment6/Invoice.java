import java.text.NumberFormat;


/**
 * This object holds information on an invoice for the paint company. It has a unique ID, name, address
 * along with information on the paint to use.
 *
 * @author Lucas Hartman
 * @version 1.0.0 - 3/21/23
 *
 */
public class Invoice implements Comparable {
    private String id;
    private String date;
    private String lastName;
    private String firstName;
    private String address;
    private String paint;
    private PaintFinish finish;
    private String color;
    private double cost;

    /**
     * Number formatter to format the cost to currency rate.
     */
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();

    /**
     * Main constructor that takes on all values to set up the invoice.
     * @param data a String for the unique identification of the invoice
     * @param date a String for the date in MM-DD-YYYY format
     * @param lastName a String for the last name of the customer
     * @param firstName a String for the first name of the customer
     * @param address a String for the address of the customer
     * @param paint a String for the type of paint to use
     * @param finish2 a PaintFinish enum type for the paint finish
     * @param color a String for the color of paint
     * @param cost a double for the cost of the paint job
     */
    public Invoice(String data, String date, String lastName, String firstName, String address, String paint,
                      PaintFinish finish2, String color, double cost) {
        this.id = data;
        this.date = date;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.paint = paint;
        this.finish = finish2;
        this.color = color;
        this.cost = cost;
    }

    /**
     * This will return the identifier of the invoice
     * @return a String for the id
     */
    public String getId() {
        return id;
    }

    /**
     * This will set the id of the invoice
     * @param id a String for the new identification number
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This will return the date in string format MM-DD-YYYY
     * @return A String for the date
     */
    public String getDate() {
        return date;
    }

    /**
     * This will set the date of the invoice
     * @param date a String for the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * This will return the last name of the customer
     * @return a String of the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This will set the last name of the customer on the invoice
     * @param lastName a String for the last name property
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This will return the first name of the customer
     * @return A String of first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This will set the first name of the customer on the invoice
     * @param firstName a String for the first name property
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This will return the address of the customer
     * @return a String of the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * This will set the address of the customer on the invoice
     * @param address a String for the address property
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This will return the paint brand for the job
     * @return a String of the paint to use
     */
    public String getPaint() {
        return paint;
    }

    /**
     * This will set the paint brand for the job
     * @param paint a String for the paint property
     */
    public void setPaint(String paint) {
        this.paint = paint;
    }

    /**
     * This will return the enum of PaintFinish for the job
     * @return PaintFinish for the finish
     */
    public PaintFinish getPaintFinish() {
        return finish;
    }

    /**
     * This will set the finish property for the job
     * @param paintFinish a PaintFinish enum type
     */
    public void setPaintFinish(PaintFinish paintFinish) {
        this.finish = paintFinish;
    }

    /**
     * This will return the color to use on the job.
     * @return a String for the color
     */
    public String getColor() {
        return color;
    }

    /**
     * This will set the color to use on the job
     * @param color a String for the color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * This will return the total cost of the paint job
     * @return a double value of the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * This will set the total cost of the paint job
     * @param cost a double value for the cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Formatted output of the Invoice
     * @return a String format of all the information of the paint job
     */
    @Override
    public String toString() {
        return String.format("Invoice ID: %s\nInvoice Date: %s\nCustomer Name: %s %s\nAddress: %s" +
                        "\nPaint: %s %s\nColor: %s\nTotal Cost: %s",
                id, date, firstName, lastName, address, finish.toString(), paint, color, currency.format(cost));
    }

    /**
     * Method used to save the Invoice data out to a text file. Tabs are set between each property value.
     * @return a String format of the data
     */
    public String fileWriteOut() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%.2f",
                id, date, lastName, firstName, address, paint, finish.toString(), color, cost);
    }

    /**
     * See's if this invoice is the same as the others. Compares via the id property.
     * @param obj the second object to compare
     * @return true if the objects are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Invoice) {
            Invoice i = (Invoice) obj;
            return i.getId().equalsIgnoreCase(id);
        }
        return false;
    }

    /**
     * Compares the objects via the dates. First checks year, if same year, checks month,
     * if same month then finally checks against the date
     * @param obj the object to be compared.
     * @return -1 if this object is before obj, 0 if they are the same date, and 1 if this object's date is after obj
     */
    @Override
    public int compareTo(Object obj) {
        Invoice i = (Invoice) obj;
        String[] dateSplit = date.split("-");
        int curMonth = Integer.parseInt(dateSplit[0]);
        int curDay = Integer.parseInt(dateSplit[1]);
        int curYear = Integer.parseInt(dateSplit[2]);

        String[] iDateSplit = i.getDate().split("-");
        int iMonth = Integer.parseInt(iDateSplit[0]);
        int iDay = Integer.parseInt(iDateSplit[1]);
        int iYear = Integer.parseInt(iDateSplit[2]);

        // check the year
        if (curYear < iYear) return -1;
        else if (curYear > iYear) return 1;
        // if years are the same check the months
        else {
            if (curMonth < iMonth) return -1;
            else if(curMonth > iMonth) return 1;
            // if the months are the same, check the days
            else {
                if (curDay < iDay) return -1;
                else if (curDay > iDay) return 1;
                else return 0;
            }
        }
    }
}

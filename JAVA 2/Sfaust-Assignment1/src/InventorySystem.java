/**
 * This file is part of INFO 1531 Module 1 assignment. It is used to check the proper implementation of
 * reading and writing out to a file. None of the code here should be changed. Compare the output and
 * modification that it does to the text file.
 *
 * @author Lucas Hartman
 * @version 1.0 - 02/06/2023
 *
 */
public class InventorySystem {
    /**
     * The main method for running the project
     * @param args String array of inputs - not needed
     */
    public static void main(String[] args) {
        InventoryDB db = new InventoryDB("storeproducts.txt"); // send file name off to the object

        db.readInProducts(); // read in products

        // **** un comment the line below to use for your check to make sure everything reads in
        //db.displayProducts(); // print out
        // *****

        // /* Uncomment this line comment to not do the add/sell yet use the displayProducts first to check read in.
        // add items here
        db.addInventory("040634", 3); // MCC long sleeve was 3, add 3 more
        db.addInventory("MMS02168", 10); // Hyrdoflask was 5, add 10 more
        db.addInventory("237834", 28); // dr pepper was 46, add 28 more
        db.addInventory("807229", 12); // payday candy bar was 24, add 12 more

        // sell items here
        db.sellItem("182322", 3);
        db.sellItem("182476", 10);
        db.sellItem("044251", 5);
        db.sellItem("040632", 1);

        // add items to the list
        Item newItem1 = new Item("M&M", "Mars", "359685", 26, 1.38);
        Item newItem2 = new Item("Peanut M&M", "Mars", "359852", 26, 1.43);
        Item newItem3 = new Item("Peanut Butter M&M", "Mars", "359998", 26, 1.63);

        db.addNewItem(newItem1);
        db.addNewItem(newItem2);
        db.addNewItem(newItem3);

        // */

        // save out new inventory numbers
        db.writeOutProducts();

    }
}

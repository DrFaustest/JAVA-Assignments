import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This file is part of INFO 1531 Module 1 assignment.
 *
 * This class is our "database" that will read in and write out to the text file.
 * It creates an ArrayList of Items and reads in to the given text file name.
 *
 * ASSIGNMENT:  You are to fill in the readInProducts() and writeOutProducts() methods here.
 *
 * @author Lucas Hartman with modifications by Scott Faust
 * 
 * Resources: https://www.w3schools.com/java/java_files_read.asp
 *
 * @version 1.0 - 02/06/23
 */
public class InventoryDB {
    private ArrayList<Item> itemList;
    private String fileName;

    /**
     * Default constructor that opens up a file called "products.txt" and sets up the arraylist
     */
    public InventoryDB(){
        itemList = new ArrayList<Item>();
        fileName = "products.txt";
    }

    /**
     * Constructor that takes a file name to open and sets up the arraylsit
     * @param fileName a String of the file name to open for read and write
     */
    public InventoryDB(String fileName){
        itemList = new ArrayList<Item>();
        this.fileName = fileName;
    }

    /**
      * Returns back the items list
      * @return an ArrayList of Items
     */
    public ArrayList<Item> getProductList() {
        return itemList;
    }

    /**
     * YOUR TO DO ITEM
     *
     * This method will open a file, read in the contents and creates a new Item for each line,
     * then add it to the ArrayList.
     */
    public void readInProducts() {
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            // skip the first line (header)
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("\t");
                String name = parts[0];
                String manufacturer = parts[1];
                String id = parts[2];
                int invintory = Integer.parseInt(parts[3]);
                double price = Double.parseDouble(parts[4]);
                Item item = new Item(name, manufacturer, id, invintory, price);
                itemList.add(item);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    

    /**
     * YOUR TO DO ITEM
     *
     * This method will open a file and overwrite the entire file saving over the contents in the
     * itemList ArrayList. This updates the qty and any **new** item that could have been added.
     */
    public void writeOutProducts() {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            // write the header line
            myWriter.write("productname\tmanufacturer\tid\tinventory\tprice\n");
            for (Item item : itemList) {
                myWriter.write(item.getName() + "\t"+item.getManufacturer()+ "\t" +item.getId()+ "\t" +item.getInventory() + "\t" + item.getPrice() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    

    /**
     * This will print out all the items
     */
    public void displayProducts() {
        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    /**
     * This will add to the inventory of an item based on ID. This uses searchItem to make sure we have a matching id.
     * @param id a String for the item id in the list
     * @param qty an int for the amount to add to the inventory of an item.
     */
    public void addInventory(String id, int qty) {
        Item item = searchItemID(id);
        if (item != null)
        {
            if(item.addInventory(qty)) {
                System.out.println("Inventory Updated");
            }
        }
    }

    /**
     * This will subtract from the inventory of an item based on the id sent. This uses searchItem to make
     * sure we have a matching id.
     * @param id a String for the item id in the list
     * @param qty an int for the amount to sell from inventory of the item.
     */
    public void sellItem(String id, int qty) {
        Item item = searchItemID(id);
        if (item != null)
        {
            if(item.sellProduct(qty)) {
                System.out.println("Item(s) Sold");
            }
        }
    }

    /**
     * This will return back an item in the list searching for the id of it.
     *
     * @param id a String used to find an item in the list.
     * @return the reference to the Item in the ArrayList if it is there, null otherwise
     */
    public Item searchItemID(String id) {
        Item foundItem = null;
        for (Item item : itemList)
        {
            if (item.getId().equals(id)) {
                foundItem = item;
                break;
            }
        }
        return foundItem;
    }

    /**
     * This will add the new item in the list if it is not already in the system. This uses itemInList() to check.
     * @param newItem an Item to add to the current list
     * @return True if the item was added successfully, false otherwise
     */
    public boolean addNewItem(Item newItem){
        if (!itemInList(newItem))
        {
            itemList.add(newItem);
            System.out.println("New Item Added");
            return true;
        }
        System.out.println("Item already in products.");
        return false;
    }

    /**
     * This checks if a matching item is in the list or not.
     *
     * @param item The item to search for in the list
     * @return True if the item sent was in the list, false otherwise.
     */
    public boolean itemInList(Item item) {
        for (Item i : itemList)
        {
            if (i.equals(item)) {
                return true;
            }
        }
        return false;
    }

}

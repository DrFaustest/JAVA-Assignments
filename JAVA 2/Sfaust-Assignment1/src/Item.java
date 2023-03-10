import java.text.NumberFormat;

/**
 * This file is part of INFO 1531 Module 1 assignment. This is the object that represents each item
 * in our inventory system. Nothing should be changed here, but use this to reference as needed.
 *
 * @author Lucas Hartman
 *
 * @version 1.0 - 02/06/23
 */
public class Item {
    private String name;
    private String manufacturer;
    private String id;
    private int inventory;
    private double price;
    private NumberFormat currency = NumberFormat.getCurrencyInstance();

    /**
     * The constructor that creates the object.
     * @param name String of the product name
     * @param manufacturer String of the manufacturer of the product
     * @param id String for the id/UPC/unique code
     * @param inventory int of the amount on hand
     * @param price double of the price of the product
     */
    public Item(String name, String manufacturer, String id, int inventory, double price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.id = id;
        this.inventory = inventory;
        this.price = price;
    }

    /**
     * returns the name
     * @return a String
     */
    public String getName() {
        return name;
    }

    /**
     * returns the manufacturer
     * @return a String
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * returns the id of product
     * @return a String
     */
    public String getId() {
        return id;
    }

    /**
     * returns the current inventory of product
     * @return an int
     */
    public int getInventory() {
        return inventory;
    }

    /**
     * returns the price of the product
     * @return a double
     */
    public double getPrice() {
        return price;
    }

    /**
     * updates the name of the product
     * @param name a String for the updated product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * updates the manufacturer of the product
     * @param manufacturer a String for the updated manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * updates the id of the product
     * @param id a String for the updated id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * updates the price of the product
     * @param price a double for the updated price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Adds to the inventory of the product. Makes sure the quantity to add is positive
     * @param count an int to add to the current inventory
     * @return returns true if successfully added, false otherwise.
     */
    public boolean addInventory(int count) {
        if (count > 0) {
            inventory += count;
            return true;
        }
        return false;
    }

    /**
     * Subtracts from the inventory of the product for when an item is sold. Makes sure the inventory is
     * available before performing this action.
     * @param count an int for the amount to sell
     * @return True if the operation was successful, false otherwise (not enough in inventory)
     */
    public boolean sellProduct(int count){
        if (inventory >= count) {
            inventory -= count;
            return true;
        }
        return false;
    }

    /**
     * Prints out the product
     * @return a String
     */
    @Override
    public String toString() {
        return "Name: " + name + "\nManufacturer: " + manufacturer + "\nProduct Code: " + id +
                "\nInventory: " + inventory + "\nPrice: " + currency.format(price);
    }

    /**
     * Comapres the objects via the id field.
     * @param obj an object to compare
     * @return True if the objects match up, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item i2) {
            if (i2.getId().equalsIgnoreCase(this.id))
            {
                return true;
            }
        }
        return false;
    }
}

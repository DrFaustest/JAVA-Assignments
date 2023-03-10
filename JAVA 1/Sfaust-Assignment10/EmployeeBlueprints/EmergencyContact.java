/**
 * @author Scott Faust
 * @version 1.0
 * date: 2/19/2023
 * class: 22_WI_INFO_1521_WW
 * resources: Class lecture videos, Murach Java Programming, and the Java API
 * Description:
 * This is the EmergencyContact class. It contains the following fields: name, relationship, and phone, and the following methods: getName(), setName(), getRelationship(), setRelationship(), getPhone(), setPhone(), and printContact()
 */
package EmployeeBlueprints;


/**
* This class represents an emergency contact for an employee.
*/
public class EmergencyContact {

    private String name;
    private String relationship;
    private String phone;

    /**
     * Creates a new emergency contact with the specified name, relationship, and phone number.
     * 
     * @param n the name of the emergency contact
     * @param r the relationship of the emergency contact to the employee
     * @param p the phone number of the emergency contact
     */
    public EmergencyContact(String n, String r, String p) {
        name = n;
        relationship = r;
        phone = p;
    }
    
    /**
     * Returns the name of the emergency contact.
     * 
     * @return the name of the emergency contact
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the emergency contact.
     * 
     * @param n the new name of the emergency contact
     */
    public void setName(String n) {
        name = n;
    }
    
    /**
     * Returns the relationship of the emergency contact to the employee.
     * 
     * @return the relationship of the emergency contact
     */
    public String getRelationship() {
        return relationship;
    }
    
    /**
     * Sets the relationship of the emergency contact to the employee.
     * 
     * @param r the new relationship of the emergency contact
     */
    public void setRelationship(String r) {
        relationship = r;
    }
    
    /**
     * Returns the phone number of the emergency contact.
     * 
     * @return the phone number of the emergency contact
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Sets the phone number of the emergency contact.
     * 
     * @param p the new phone number of the emergency contact
     */
    public void setPhone(String p) {
        phone = p;
    }
    
    /**
     * Prints the name, relationship, and phone number of the emergency contact to the console.
     */
    public void printContact() {
        System.out.println("Name: " + name + "\nRelationship: " + relationship + "\nPhone: " + phone);
    }
}
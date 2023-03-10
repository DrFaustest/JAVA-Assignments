/*Name: Scott Faust
 * Class: 22_WI_INFO_1521_WW
 * Date: 2/5/2023
 * Resources: Class lecture videos, Murach Java Programming, and the Java API
 * 
 * Description: This class is a template for an employee Emergency Contact object. It contains the following fields:
 * name, relationship, phone with getters and setters for each
 * 
 */ 



/*
-name : String 
-relationship : String 
-phone : String 
<<constructor>>EmergencyContact(String n, String r, String p) 
+getName() : String 
+setName(String n) : void 
+getRelationship() : String 
+setRelationship(String r) : void 
+getPhone() : String 
+setPhone(String p) : void 
+printContact() : void
*/



public class EmergencyContact {
    /*
-name : String 
-relationship : String 
-phone : String 
     */
    private String name;
    private String relationship;
    private String phone;
    /*
<<constructor>>EmergencyContact(String n, String r, String p) 
+getName() : String 
+setName(String n) : void 
+getRelationship() : String 
+setRelationship(String r) : void 
+getPhone() : String 
+setPhone(String p) : void 
+printContact() : void
     */
    public EmergencyContact(String n, String r, String p) {
        name = n;
        relationship = r;
        phone = p;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public String getRelationship() {
        return relationship;
    }
    
    public void setRelationship(String r) {
        relationship = r;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String p) {
        phone = p;
    }
    
    public void printContact() {
        System.out.println("Name: " + name + "\nRelationship: " + relationship + "\nPhone: " + phone);
    }
    
}

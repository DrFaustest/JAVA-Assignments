/**
 * @author: Scott Faust
 * @version: 1.0
 * date: 2/19/2023
 * class: 22_WI_INFO_1521_WW
 * resources: Class lecture videos, Murach Java Programming, and the Java API
 * Description:
 * This is the Printable interface. It contains the following fields: percent, currency, and the following methods: print()
 */

 package EmployeeBlueprints;

 import java.text.NumberFormat;
 
 /**
  * This interface defines a contract for objects that can be printed.
  */
 public interface Printable {
     
     /**
      * This method prints the object.
      */
     public void print();
     
     /**
      * This constant provides a percentage formatter.
      */
     NumberFormat percent = NumberFormat.getPercentInstance();
     
     /**
      * This constant provides a currency formatter.
      */
     NumberFormat currency = NumberFormat.getCurrencyInstance();
 }

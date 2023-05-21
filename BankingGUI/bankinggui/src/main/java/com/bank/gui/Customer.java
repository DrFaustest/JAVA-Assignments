package com.bank.gui;
/**
 * Customer class
 * See App.java for full description
 * @author Scott Faust
 * @version 1.0 - 2023-05-20
 *
 */
    public class Customer {
        private int id;
        private String firstName;
        private String lastName;
        private String address;
        private String phoneNumber;
        private int accountId;
    
        
        /**
         * The Customer class with getters and setters
         */
        public Customer() {
        }
        public Customer (int id, String firstName, String lastName, String address, String phoneNumber, int accountId) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.accountId = accountId;
        }
    
        public Customer(String firstName, String lastName, String address, String phoneNumber, int accountId) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.accountId = accountId;
        }
    
        // Getters
        public int getId() {
            return id;
        }
    
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    
        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    
        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        @Override
        public String toString() {
        return "First: " + firstName + " | Last: " + lastName + 
           " | Address: " + address + " | Phone: " + phoneNumber;
}

}
    

package com.bank.gui;
import java.util.List;
/**
 * Customer Management class
 * See App.java for full description
 * @author Scott Faust
 * @version 1.0 - 2023-05-20
 *
 */
public class CustomerManagement {
 
    private DatabaseUtil databaseUtil;
 
    /**
     * This Method is for managing customers
     */
    public CustomerManagement() {
        databaseUtil = new DatabaseUtil();
    } 
 
    /**
     * This method searches for customers by name and returns a list of customers
     * that match the search criteria
     * @param firstName
     * @param lastName
     * @return List<Customer>
     */
    public List<Customer> searchCustomersByName(String firstName, String lastName) {
        return databaseUtil.searchCustomersByName(firstName, lastName);
    }
 
    /**
     * This method retrieves the account information for a customer
     * given the accountId and returns an Account object
     * @param accountId
     * @return Account
     */
    public Account retrieveSavingsAccount(int accountId) {
        double balance = databaseUtil.getAccountBalance(accountId);
        double interestRate = databaseUtil.getAccountInterestRate(accountId);
        return new Account(accountId, balance, interestRate);
    }
    
    /**
     * Given a customer profile information, this method updates the information in the database
     * @param customerId
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     */
    public void editCustomerProfile(int customerId, String firstName, String lastName, String address, String phoneNumber) {
        databaseUtil.editCustomerProfile(customerId, firstName, lastName, address, phoneNumber);
    }
    
    /**
     * Given a customer profile information, this method links the customer to an existing account
     * @param customerId
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     */
    public void linkToExistingAccount(int customerId, String firstName, String lastName, String address, String phoneNumber) {
        int accountId = databaseUtil.getAccountId(firstName, lastName);
        if(accountId != -1) {
            databaseUtil.linkToExistingAccount(customerId, accountId);
        }
    }
    
    /**
     * This method creates a new account and links it to a new customer
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param initialDeposit
     * @param interestRate
     */
    public void createNewAccount(String firstName, String lastName, String address, String phoneNumber, double initialDeposit, double interestRate) {
        int accountId = databaseUtil.createNewAccount(initialDeposit, interestRate);
        if (accountId != -1) {
            databaseUtil.addCustomer(firstName, lastName, address, phoneNumber, accountId);
        }
    }
    
    /**
     * This method finds a customer given the customer profile information
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @return -1 if not found, otherwise returns the customer id
     */
    public int findCustomer(String firstName, String lastName, String address, String phoneNumber) {
        return databaseUtil.findCustomer(firstName, lastName, address, phoneNumber);
    }
    
    /**
     * This method closes an account given the customer id and account id
     * @param customerId
     * @param accountId
     */
    public void closeAccount(int customerId, int accountId) {
        databaseUtil.closeAccount(customerId, accountId);
    }
}

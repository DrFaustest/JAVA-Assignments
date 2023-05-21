package com.example.bankingsystem;

import java.sql.SQLException;
import java.sql.ResultSet;

public class TestFunctionality {
    public static void main(String[] args) {
        Customer customer = new Customer(1, "John", "Doe", "1234 Main St", "123-456-7890");
        Account account = new Account(1, "123456789", 100.00, 0.05, 1);
        Transaction transaction = new Transaction(1, "deposit", 100.00, 1);

        try {
            Database database = new Database();
            database.addCustomer(customer);
            database.addAccount(account);
            database.addTransaction(transaction);
            
            // Ensure you process the ResultSet here and then close it.
            // For simplicity, just printing it here and closing
            ResultSet customer1 = database.getCustomer(1);
            System.out.println(customer1.toString());
            customer1.close();
            
            ResultSet account1 = database.getAccount(1);
            System.out.println(account1.toString());
            account1.close();
            
            Transaction transaction1 = (Transaction) database.getTransaction(1);
            System.out.println(transaction1.toString());
            
            // Create new objects with updated information for the update methods
            Customer updatedCustomer = new Customer(1, "Jane", "Doe", "1234 Main St", "123-456-7890");
            Account updatedAccount = new Account(1, "987654321", 200.00, 0.05, 1);
            Transaction updatedTransaction = new Transaction(1, "withdraw", 50.00, 1);
            
            database.updateCustomer(updatedCustomer);
            database.updateAccount(updatedAccount);
            database.updateTransaction(updatedTransaction);
            
            database.deleteCustomer(1);
            database.deleteAccount(1);
            database.deleteTransaction(1);
            
            // Finally, disconnect the database connection
            database.disconnect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }  
}

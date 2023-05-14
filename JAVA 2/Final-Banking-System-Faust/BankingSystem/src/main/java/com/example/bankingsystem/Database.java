package com.example.bankingsystem;

import java.sql.*;

class Database {
    private static Connection connection = null;
    // Connection object for database
    Connection getConnection() throws SQLException {
        if (connection == null) {
            String dbUrl = "jdbc:sqlite:banking.db";
            connection = DriverManager.getConnection(dbUrl);
        } return connection;
    }

    void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
        


    void addCustomer(Customer customer) {
        // Connect to the database
        getConnection();
        // Insert the customer into the Customer table
        String sql = "INSERT INTO Customer (firstName, lastName, address, phoneNumber) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customer.getFirstName());
        statement.setString(2, customer.getLastName());
        statement.setString(3, customer.getAddress());
        statement.setString(4, customer.getPhoneNumber());
        statement.executeUpdate();
        // Disconnect from the database
        disconnect();
    }

    ResultSet getCustomer(int id) {
        // Connect to the database
        connection = getConnection();
        // Fetch the customer with the given id from the Customer table
        String sql = "SELECT * FROM Customer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        // Disconnect from the database
        disconnect();
        // Return the fetched customer
        return result;
    }

    void addAccount(Account account) {
        // Connect to the database
        connection = getConnection();
        // Insert the account into the Account table
        String sql = "INSERT INTO Account (customerId, balance) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, account.getCustomerId());
        statement.setDouble(2, account.getBalance());
        statement.executeUpdate();
        // Disconnect from the database
        disconnect();
    }

    ResultSet getAccount(int id) {
        // Connect to the database
        connection = getConnection();
        // Fetch the account with the given id from the Account table
        String sql = "SELECT * FROM Account WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        // Disconnect from the database
        disconnect();
        // Return the fetched account
        return result;
    }

    ResultSet getAccountByCustomerId(int customerId) throws SQLException {
        // Connect to the database
        connection = getConnection();
        // Fetch the account(s) with the given customerId from the Account table
        String sql = "SELECT * FROM Account WHERE customerId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, customerId);
        ResultSet result = statement.executeQuery();
        // Disconnect from the database
        disconnect();
        // Return the fetched account(s)
        return result;
    }
}


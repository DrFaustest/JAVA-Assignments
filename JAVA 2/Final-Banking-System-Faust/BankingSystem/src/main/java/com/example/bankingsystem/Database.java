package com.example.bankingsystem;
import java.sql.*;
class Database {
    private static Connection connection = null;
    Connection getConnection() throws SQLException {
        try {
        if (connection == null) {
            String dbUrl = "jdbc:sqlite:banking.db";
            connection = DriverManager.getConnection(dbUrl);
        } return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();}
    }
    void addCustomer(Customer customer) throws SQLException {
        getConnection();
        String sql = "INSERT INTO Customer (firstName, lastName, address, phoneNumber) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customer.getFirstName());
        statement.setString(2, customer.getLastName());
        statement.setString(3, customer.getAddress());
        statement.setString(4, customer.getPhoneNumber());
        statement.executeUpdate();
        disconnect();
    }
    ResultSet getCustomer(int id) throws SQLException {
        connection = getConnection();
        String sql = "SELECT * FROM Customer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        disconnect();
        return result;
    }
    void addAccount(Account account) throws SQLException {
        connection = getConnection();
        String sql = "INSERT INTO Account (customerId, balance) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, account.getCustomerId());
        statement.setDouble(2, account.getBalance());
        statement.executeUpdate();
        disconnect();
    }
    ResultSet getAccount(int id) throws SQLException {
        connection = getConnection();
        String sql = "SELECT * FROM Account WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        disconnect();
        return result;
    }
    ResultSet getAccountByCustomerId(int customerId) throws SQLException {
        connection = getConnection();
        String sql = "SELECT * FROM Account WHERE customerId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, customerId);
        ResultSet result = statement.executeQuery();
        disconnect();
        return result;
    }
    void deleteAccount(int id) throws SQLException {
        connection = getConnection();
        String sql = "DELETE FROM Account WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        disconnect();
    }
    void addTransaction(Transaction transaction) throws SQLException {
        connection = getConnection();
        String sql = "INSERT INTO Transaction (accountId, type, amount) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, transaction.getAccountId());
        statement.setString(2, transaction.getTransactionType());
        statement.setDouble(3, transaction.getAmount());
        statement.executeUpdate();
        disconnect();
    }
    ResultSet getTransaction(int id) throws SQLException {
        connection = getConnection();
        String sql = "SELECT * FROM Transaction WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        disconnect();
        return result;
    }
    ResultSet updateTransaction(Transaction transaction1) throws SQLException {
        connection = getConnection();
        String sql = "UPDATE Transaction SET accountId = ?, type = ?, amount = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, transaction1.getAccountId());
        statement.setString(2, transaction1.getTransactionType());
        statement.setDouble(3, transaction1.getAmount());
        statement.setInt(4, transaction1.getId());
        statement.executeUpdate();
        disconnect();
        return null;
    }
    ResultSet getTransactionByAccountId(int accountId) throws SQLException {
        connection = getConnection();
        String sql = "SELECT * FROM Transaction WHERE accountId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, accountId);
        ResultSet result = statement.executeQuery();
        disconnect();
        return result;
    }
    void deleteTransaction(int id) throws SQLException {
        connection = getConnection();
        String sql = "DELETE FROM Transaction WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        disconnect();
    }
    void updateCustomer (Customer customer1) throws SQLException {
        connection = getConnection();
        String sql = "UPDATE Customer SET firstName = ?, lastName = ?, address = ?, phoneNumber = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customer1.getFirstName());
        statement.setString(2, customer1.getLastName());
        statement.setString(3, customer1.getAddress());
        statement.setString(4, customer1.getPhoneNumber());
        statement.setInt(5, customer1.getId());
        statement.executeUpdate();
        disconnect();
    }
    void deleteCustomer(int id) throws SQLException {
        connection = getConnection();
        String sql = "DELETE FROM Customer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        disconnect();
    }
    void updateAccount (Account account1) throws SQLException {
        connection = getConnection();
        String sql = "UPDATE Account SET customerId = ?, balance = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, account1.getCustomerId());
        statement.setDouble(2, account1.getBalance());
        statement.setInt(3, account1.getId());
        statement.executeUpdate();
        disconnect();
    }
    void deleteAccountByCustomerId(int customerId) throws SQLException {
        connection = getConnection();
        String sql = "DELETE FROM Account WHERE customerId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, customerId);
        statement.executeUpdate();
        disconnect();
    }
}


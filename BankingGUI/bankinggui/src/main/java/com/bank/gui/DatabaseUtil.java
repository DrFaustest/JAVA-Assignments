package com.bank.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;
/**
 * DatabaseUtil class
 * See App.java for full description
 * @author Scott Faust
 * @version 1.0 - 2023-05-20
 *
 */
public class DatabaseUtil {

    private static final String DB_URL = "jdbc:sqlite:bankinggui/src/main/resources/com/bank/gui/db/BankMainDatabase.db";
    private static Connection connection;

    /**
     * Attempt to connect to the database
     */
    public DatabaseUtil() {
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the connection to the database
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Disconnect from the database
     */
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the account balance from the database
     * @param accountId
     * @return the account balance
     */
    public static double getAccountBalance(int accountId) {
        double balance = 0.0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT balance FROM Account WHERE id = ?");
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    /**
     * Updates the account balance in the database
     * @param accountId
     * @param newBalance
     */
    public void updateAccountBalance(int accountId, double newBalance) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Account SET balance = ? WHERE id = ?");
            statement.setDouble(1, newBalance);
            statement.setInt(2, accountId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the account interest rate from the database
     * @param accountId
     * @return the interest rate
     */
    public static double getAccountInterestRate(int accountId) {
        double interestRate = 0.0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT interestRate FROM Account WHERE id = ?");
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                interestRate = resultSet.getDouble("interestRate");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interestRate;
    }

    /**
     * Adds an account transaction to the database
     * @param transaction
     */
    public void addAccountTransaction(AccountTransaction transaction) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO AccountTransaction (accountId, type, amount, transactionDate) VALUES (?, ?, ?, ?)");
            statement.setInt(1, transaction.getAccountId());
            statement.setString(2, transaction.getType());
            statement.setDouble(3, transaction.getAmount());
            statement.setDate(4, transaction.getDate());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds a list of all customer transactions from the database for a given
     * time period ordered by date
     * @param accountId
     * @param startDate
     * @param endDate
     * @return the list of transactions
     */
    public static String generateAccountStatement(int accountId, Date startDate, Date endDate) {
        StringBuilder accountStatement = new StringBuilder();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM AccountTransaction WHERE accountId = ? AND transactionDate >= ? AND transactionDate <= ? ORDER BY transactionDate");
            preparedStatement.setInt(1, accountId);
            preparedStatement.setLong(2, startDate.getTime());
            preparedStatement.setLong(3, endDate.getTime());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Date transactionDate = new Date(resultSet.getLong("transactionDate"));
                accountStatement.append(transactionDate).append(" ").append(resultSet.getString("type")).append(" ")
                        .append(resultSet.getDouble("amount")).append("\n");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountStatement.toString();
    }

    /**
     * Updates the customer profile in the database
     * @param customerId
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     */
    public void editCustomerProfile(int customerId, String firstName, String lastName, String address,
            String phoneNumber) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Customer SET firstName = ?, lastName = ?, address = ?, phoneNumber = ? WHERE id = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setInt(5, customerId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the customer's account to the given account id
     * @param customerId
     * @param accountId
     * @return the customer id
     */
    public int linkToExistingAccount(int customerId, int accountId) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Customer SET accountId = ? WHERE id = ?");
            statement.setInt(1, accountId);
            statement.setInt(2, customerId);
            statement.executeUpdate();
            statement.close();
            return customerId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Creates a new account in the database
     * @param initialDeposit
     * @param interestRate
     * @return the account id of the new account
     */
    public int createNewAccount(double initialDeposit, double interestRate) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Account (balance, interestRate) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, initialDeposit);
            statement.setDouble(2, interestRate);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                int accountId = -1;
                if (generatedKeys.next()) {
                    accountId = generatedKeys.getInt(1);
                }
                return accountId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Deletes a customer from the database via a set of cascading deletes
     * 1. If the account is shared with another customer, delete the customer
     * 2. If the account is not shared with another customer, Check the balance of the account
     * if its greater than 0, do not delete the account
     * if its 0, delete the customer, account, and all transactions for data integrity
     * @param customerId
     * @param accountId
     */
    public void closeAccount(int customerId, int accountId) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT COUNT(*) as count FROM Customer WHERE accountId = ?");
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            int linkedCustomers = resultSet.next() ? resultSet.getInt("count") : -1;
            resultSet.close();
            statement.close();

            if (linkedCustomers > 1) {
                statement = connection.prepareStatement("DELETE FROM Customer WHERE id = ?");
                statement.setInt(1, customerId);
                statement.executeUpdate();
                statement.close();
            } else {
                statement = connection.prepareStatement("SELECT balance FROM Account WHERE id = ?");
                statement.setInt(1, accountId);
                resultSet = statement.executeQuery();
                double balance = resultSet.next() ? resultSet.getDouble("balance") : -1;
                resultSet.close();
                statement.close();
                if (balance > 0) {
                    System.out.println("Unable to close account. Account balance is not zero.");
                } else {
                    statement = connection.prepareStatement("DELETE FROM Customer WHERE id = ?");
                    statement.setInt(1, customerId);
                    statement.executeUpdate();
                    statement.close();

                    statement = connection.prepareStatement("DELETE FROM AccountTransaction WHERE accountId = ?");
                    statement.setInt(1, accountId);
                    statement.executeUpdate();
                    statement.close();

                    statement = connection.prepareStatement("DELETE FROM Account WHERE id = ?");
                    statement.setInt(1, accountId);
                    statement.executeUpdate();
                    statement.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the customer id given the customer's first name, last name, address, and phone number
     * (intended to be used to find a customer that already exists in the database with account creation)
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @return the customer id
     */
    public int findCustomer(String firstName, String lastName, String address, String phoneNumber) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(
                    "SELECT id FROM Customer WHERE firstName = ? AND lastName = ? AND address = ? AND phoneNumber = ?");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setString(4, phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            int customerId = resultSet.next() ? resultSet.getInt("id") : -1;
            resultSet.close();
            statement.close();
            disconnect();
            return customerId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Gets the account id of the customer with the given first and last name
     * @param firstName
     * @param lastName
     * @return the account id of the customer with the given first and last name
     */
    public int getAccountId(String firstName, String lastName) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT accountId FROM Customer WHERE firstName = ? AND lastName = ?");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            int accountId = resultSet.next() ? resultSet.getInt("accountId") : -1;
            resultSet.close();
            statement.close();
            return accountId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Creates a new customer in the database
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param accountId
     * @return the customer id of the newly created customer
     */
    public int addCustomer(String firstName, String lastName, String address, String phoneNumber, int accountId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Customer (firstName, lastName, address, phoneNumber, accountId) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setString(4, phoneNumber);
            statement.setInt(5, accountId);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int customerId = -1;
            if (generatedKeys.next()) {
                customerId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
            statement.close();
            return customerId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Gets a list of all customers in the database that match the given first and last name
     * @param firstName
     * @param lastName
     * @return a list of all customers in the database that match the given first and last name
     */
    public List<Customer> searchCustomersByName(String firstName, String lastName) {
        List<Customer> matchingCustomers = new ArrayList<>();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Customer WHERE firstName = ? AND lastName = ?");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                int accountId = resultSet.getInt("accountId");
                Customer customer = new Customer(customerId, firstName, lastName, address, phoneNumber, accountId);
                matchingCustomers.add(customer);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchingCustomers;
    }

    /**
     * Build an account object from the given account id
     * @param accountId
     * @return an account object with the given account id
     */
    public static Account createAccountFromId(int accountId) {
        double accountBalance = getAccountBalance(accountId);
        double interestRate = getAccountInterestRate(accountId);
        Account account = new Account(accountId, accountBalance, interestRate);
        return account;
    }
}
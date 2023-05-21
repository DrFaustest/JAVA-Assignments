package com.bank.gui;
/**
 * Account Operations class
 * See App.java for full description
 * @author Scott Faust
 * @version 1.0 - 2023-05-20
 *
 */
import java.sql.Date;
import java.time.LocalDate;

public class AccountOperations {

    private static DatabaseUtil databaseUtil = new DatabaseUtil();

    public AccountOperations(DatabaseUtil databaseUtil) {
        AccountOperations.databaseUtil = databaseUtil;
    }

    
    /** 
     * This method checks the account balance
     * @param account
     * @return double balance
     */
    public double checkAccountBalance(Account account) {
        double balance = DatabaseUtil.getAccountBalance(account.getId());
        return balance;
    }

    /**
     * This method deposits funds into the account and adds a transaction to the database
     * @param account
     * @param amount
     */
    public static void depositFunds(Account account, double amount) {
        Date currentDate = Date.valueOf(LocalDate.now());
        double currentBalance = DatabaseUtil.getAccountBalance(account.getId());
        double newBalance = currentBalance + amount;
        databaseUtil.updateAccountBalance(account.getId(), newBalance);

        AccountTransaction transaction = new AccountTransaction(account.getId(), "Deposit", amount, currentDate);
        databaseUtil.addAccountTransaction(transaction);
    }

    /**
     * This method withdraws funds from the account and adds a transaction to the database
     * @param account
     * @param amount
     */
    public static void withdrawFunds(Account account, double amount) {
        Date currentDate = Date.valueOf(LocalDate.now());
        double currentBalance = DatabaseUtil.getAccountBalance(account.getId());
        if (currentBalance >= amount) {
            double newBalance = currentBalance - amount;
            databaseUtil.updateAccountBalance(account.getId(), newBalance);
            AccountTransaction transaction = new AccountTransaction(account.getId(), "Withdrawal", -amount,
                    currentDate);
            databaseUtil.addAccountTransaction(transaction);
        } else {
            throw new IllegalStateException("Insufficient balance. Withdrawal denied.");
        }
    }

    /**
     * Calculate the total interest for the year so far
     * @param account
     * @return double total interest
     */
    public static double calculateTotalInterestForYear(Account account) {
        double interestRate = DatabaseUtil.getAccountInterestRate(account.getId());
        double balance = DatabaseUtil.getAccountBalance(account.getId());
        Date currentDate = Date.valueOf(LocalDate.now());
        int currentMonth = currentDate.toLocalDate().getMonthValue();
        double monthsPassed = currentMonth - 1;
        return balance * interestRate * monthsPassed / 12;
    }

    /**
     * Generate the account statement for the given date range
     * @param account
     * @param startDate
     * @param endDate
     * @return String account statement
     */
    public String generateAccountStatement(Account account, Date startDate, Date endDate) {
        String accountStatement = databaseUtil.generateAccountStatement(account.getId(), startDate, endDate);
        return accountStatement;
    }
}

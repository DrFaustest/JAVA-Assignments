package com.bank.gui;
/**
 * Account class
 * See App.java for full description
 * @author Scott Faust
 * @version 1.0 - 2023-05-20
 *
 */
public class Account {
    private int id;
    private double balance;
    private double interestRate;

    /** 
     * Account class
     * With getters and setters
     * @return Account
     * @param id
     * @param balance
     * @param interestRate
     */

    public Account() {
    }

    public Account(int id, double balance, double interestRate) {
        this.id = id;
        this.balance = balance;
        this.interestRate = interestRate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}

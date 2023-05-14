package com.example.bankingsystem;

import java.util.List;

public class SavingsAccount {
    private int id;
    private String accountNumber;
    private double balance;
    private double interestRate;
    private List<Customer> accountOwners;

    public SavingsAccount(int id, String accountNumber, double balance, double interestRate, List<Customer> accountOwners) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountOwners = accountOwners;
    }

    public int getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public List<Customer> getAccountOwners() {
        return accountOwners;
    }
}
package com.example.bankingsystem;

public class Transaction {
    private int id;
    private String transactionType;
    private double amount;
    private int accountId;
    public Transaction(int id, String transactionType, double amount, int accountId) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountId = accountId;
    }
    public int getId() {
        return id;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public double getAmount() {
        return amount;
    }
    public int getAccountId() {
        return accountId;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", accountId=" + accountId +
                '}';
    }
}

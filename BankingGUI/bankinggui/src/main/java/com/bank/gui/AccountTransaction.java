package com.bank.gui;
import java.sql.Date;
/**
 * Account Transaction class
 * See App.java for full description
 * @author Scott Faust
 * @version 1.0 - 2023-05-20
 *
 */
public class AccountTransaction {
    private int id;
    private int accountId;
    private String type;
    private double amount;
    private Date date;

    // Constructors
    /**
     * The AccountTransaction class with getters and setters
     */
    public AccountTransaction() {
    }

    public AccountTransaction(int accountId, String type, double amount, Date date) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}


package com.example.bankingsystem;

import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Integer savingsAccountNumber;

    public Customer(int id, String firstName, String lastName, String address, String phoneNumber,
            List<SavingsAccount> savingsAccounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.savingsAccountNumber = savingsAccounts;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getSavingsAccountNumber() {
        return savingsAccountNumber;
    }

    public void addSavingsAccount(SavingsAccount savingsAccount) {
        SavingsAccount.add(savingsAccount);
    }

}

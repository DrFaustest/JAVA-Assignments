package com.example.bankingsystem;

public enum AccountType {
    CHECKING("Checking"), SAVINGS("Savings");

    private String label;

    private AccountType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

package com.example.bankingsystem;
public class Account {
    private int id;
    private String accountNumber;
    private double balance;
    private double interestRate;
    private int customerId;
    public Account(int id, String accountNumber, double balance, double interestRate, int customerId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
        this.customerId = customerId;
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
    public int getCustomerId() {
        return customerId;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                ", customerId=" + customerId +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Account))
            return false;
        Account account = (Account) obj;
        return account.getId() == this.getId() &&
                account.getAccountNumber().equals(this.getAccountNumber()) &&
                account.getBalance() == this.getBalance() &&
                account.getInterestRate() == this.getInterestRate() &&
                account.getCustomerId() == this.getCustomerId();
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + accountNumber.hashCode();
        long balanceBits = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (balanceBits ^ (balanceBits >>> 32));
        long interestRateBits = Double.doubleToLongBits(interestRate);
        result = 31 * result + (int) (interestRateBits ^ (interestRateBits >>> 32));
        result = 31 * result + customerId;
        return result;
    }
}
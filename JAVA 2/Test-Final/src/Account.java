import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private double balance;
    private double interestRate;
    private List<Customer> owners;

    // Getters and Setters

    public List<Customer> getOwners() {
        return owners;
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

    public Account(String accountNumber, double interestRate) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.interestRate = interestRate;
        this.owners = new ArrayList<>();
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public double calculateInterest(int month) {
        return this.balance * (this.interestRate / 100) * month / 12;
    }
}

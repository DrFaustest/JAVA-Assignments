import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Account account;

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Account getAccount() {
        return account;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        if (this.address == null) {
            this.address = address;
        } else {
            System.out.println("Address already set");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (this.phoneNumber == null) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Phone number already set");
        }
    }

    public void setAccount(Account account) {
        if (this.account == null) {
            this.account = account;
        } else {
            System.out.println("Customer already has an account");
        }
    }

    public Customer(String firstName, String lastName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

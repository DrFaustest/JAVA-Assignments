package com.example.bankingsystem;
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    public Customer(int id, String firstName, String lastName, String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;

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
    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }
    public void setLastName(String lastName) {
        this.lastName = lastName;

    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", address='" + address
                + '\'' + ", phoneNumber='" + phoneNumber + '\'' + '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Customer))
            return false;
        Customer customer = (Customer) obj;
        return customer.getId() == this.getId() && customer.getFirstName().equals(this.getFirstName())
                && customer.getLastName().equals(this.getLastName()) && customer.getAddress().equals(this.getAddress())
                && customer.getPhoneNumber().equals(this.getPhoneNumber());
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        return result;
    }
}
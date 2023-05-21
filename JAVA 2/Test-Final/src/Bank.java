import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;
    private List<Account> accounts;

    public Bank() {
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public void addCustomer(String firstName, String lastName, String address, String phoneNumber) {
        Customer customer = new Customer(firstName, lastName, address, phoneNumber);
        this.customers.add(customer);
    }

    public void openAccount(String accountNumber, double interestRate, Customer customer) {
        for (Account account : accounts) {
            if (account.getOwners().contains(customer)) {
                System.out.println("Customer already has an account");
                return;
            }
        }
        Account account = new Account(accountNumber, interestRate);
        account.getOwners().add(customer);
        this.accounts.add(account);
        customer.setAccount(account);
    }

    public Account searchCustomer(String firstName, String lastName) {
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                return customer.getAccount();
            }
        }
        return null;
    }
}
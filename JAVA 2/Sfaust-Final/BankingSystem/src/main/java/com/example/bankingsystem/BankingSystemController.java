package com.example.bankingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

public class BankingSystemController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField accountNumberField;

    @FXML
    private TextField interestRateField;

    @FXML
    private TextField depositField;

    @FXML
    private TextField withdrawField;

    @FXML
    private TextField balanceField;

    private List<Customer> customers;

    private Customer currentCustomer;

    public void initialize() {
        // Load the list of customers from the XML data storage
        try {
            DatabaseFunctions databaseFunctions = new DatabaseFunctions("banking_data.xml");
            customers = databaseFunctions.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchButtonClicked() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        // Find the first customer in the list with matching first and last name
        Optional<Customer> matchingCustomer = customers.stream()
                .filter(c -> c.getFirstName().equals(firstName) && c.getLastName().equals(lastName))
                .findFirst();

        if (matchingCustomer.isPresent()) {
            // Set the current customer and display their savings account information
            currentCustomer = matchingCustomer.get();
            displaySavingsAccountInformation();
        } else {
            // Alert the user that no matching customer was found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No matching customer found.");
            alert.showAndWait();
        }
    }

    private void displaySavingsAccountInformation() {
        SavingsAccount savingsAccount = currentCustomer.getSavingsAccounts().get(0);
        accountNumberField.setText(savingsAccount.getAccountNumber());
        interestRateField.setText(String.format("%.2f", savingsAccount.getInterestRate()));
        balanceField.setText(String.format("%.2f", savingsAccount.getBalance()));
    }


    public void nextButtonClicked() {
        if (currentCustomer == null) {
            // Alert the user that no customer is currently selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please search for a customer before using the next button.");
            alert.showAndWait();
        } else {
            // Find the next customer in the list and set as current customer
            int currentIndex = customers.indexOf(currentCustomer);
            int nextIndex = (currentIndex + 1) % customers.size();
            currentCustomer = customers.get(nextIndex);
            displaySavingsAccountInformation();
        }
    }

    public void openAccountButtonClicked() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        // Find the first customer in the list with matching first and last name
        Optional<Customer> matchingCustomer = customers.stream()
                .filter(c -> c.getFirstName().equals(firstName) && c.getLastName().equals(lastName))
                .findFirst();

        if (matchingCustomer.isPresent()) {
            // Check if the customer already has a savings account
            if (!matchingCustomer.get().getSavingsAccounts().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Customer already has a savings account.");
                alert.showAndWait();
            } else {
                // Create a new savings account and add it to the customer's list of savings accounts
                List<Customer> accountCustomers = new ArrayList<>();
                accountCustomers.add(matchingCustomer.get());
                SavingsAccount savingsAccount = new SavingsAccount(0, accountNumberField.getText(), 0.0,
                        Double.parseDouble(interestRateField.getText()), accountCustomers);
                matchingCustomer.get().addSavingsAccount(savingsAccount);

                // Update the XML data storage with the new account information
                try {
                    DatabaseFunctions databaseFunctions = new DatabaseFunctions("banking_data.xml");
                    databaseFunctions.updateCustomer(matchingCustomer.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Display the new account information in the user interface
                displaySavingsAccountInformation();
            }
        } else {
            // Alert the user that no matching customer was found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No matching customer found.");
            alert.showAndWait();
        }
    }



}

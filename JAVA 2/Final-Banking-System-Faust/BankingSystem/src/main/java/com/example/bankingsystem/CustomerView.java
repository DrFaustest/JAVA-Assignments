package com.example.bankingsystem;

import java.sql.SQLException;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class CustomerView extends VBox {
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField addressField;
    private TextField phoneNumberField;
    private Button createCustomerButton;

    public CustomerView() {
        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        addressField = new TextField();
        addressField.setPromptText("Address");

        phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");

        createCustomerButton = new Button("Create Customer");
        createCustomerButton.setOnAction(e -> {
            try {
                createCustomer();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        getChildren().addAll(firstNameField, lastNameField, addressField, phoneNumberField, createCustomerButton);
    }

    private void createCustomer() throws SQLException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();

        Customer customer = new Customer(0, firstName, lastName, address, phoneNumber);

        Database db = new Database();
        db.addCustomer(customer);
    }
}

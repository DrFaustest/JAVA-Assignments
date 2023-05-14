package com.example.bankingsystem;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountView extends VBox {
    private TextField accountNumberField;
    private TextField balanceField;
    private TextField interestRateField;
    private TextField customerIdField;
    private Button createAccountButton;
    private Button calculateInterestButton;

    public AccountView() {
        accountNumberField = new TextField();
        accountNumberField.setPromptText("Account Number");

        balanceField = new TextField();
        balanceField.setPromptText("Initial Balance");
        balanceField.setEditable(false);

        interestRateField = new TextField();
        interestRateField.setPromptText("Interest Rate");

        customerIdField = new TextField();
        customerIdField.setPromptText("Customer ID");

        createAccountButton = new Button("Create Account");
        createAccountButton.setOnAction(e -> createAccount());

        calculateInterestButton = new Button("Calculate Interest");
        calculateInterestButton.setOnAction(e -> calculateInterest());

        getChildren().addAll(accountNumberField, balanceField, interestRateField, customerIdField, createAccountButton, calculateInterestButton);
    }

    private void createAccount() throws SQLException {
        String accountNumber = accountNumberField.getText();
        double interestRate = Double.parseDouble(interestRateField.getText());
        int customerId = Integer.parseInt(customerIdField.getText());

        Account account = new Account(0, accountNumber, 0.0, interestRate, customerId);

        Database db = new Database();
        db.getConnection();
        db.addAccount(account);
        db.disconnect();
    }

    private void calculateInterest() throws SQLException {
        Database db = new Database();
        db.getConnection();
        ResultSet account = db.getAccountByCustomerId(Integer.parseInt(customerIdField.getText()));
        db.disconnect();

        if (account != null) {
            double interest = account.getBalance() * account.getInterestRate() / 100;
            balanceField.setText(String.valueOf(account.getBalance() + interest));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Account not found");
            alert.showAndWait();
        }
    }
}

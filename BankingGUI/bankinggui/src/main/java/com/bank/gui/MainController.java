package com.bank.gui;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * MainController class
 * See App.java for full description
 * @author Scott Faust
 * @version 1.0 - 2023-05-20
 *
 */
public class MainController {
    @FXML
    private TextField accountBalanceDisplay;
    @FXML
    private TextField accountInterestRateDisplay;
    @FXML
    private TextField accountIdDisplay;
    @FXML
    private TextField DepositAmount;
    @FXML
    private TextField WithdrawAmount;
    @FXML
    private DatePicker transactionFromDate;
    @FXML
    private DatePicker transactionToDate;
    @FXML
    private TextArea transactionHistory;
    @FXML
    private TextArea transactionHistoryTextArea;
    @FXML
    private Button displayTransactionHistoryButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button depositButton;
    @FXML
    private TextField updateFirstName;
    @FXML
    private TextField updateLastName;
    @FXML
    private TextField updateAddress;
    @FXML
    private TextField updatePhoneNumber;
    @FXML
    private Button updateButton;
    @FXML
    private Button searchCustomerButton;
    @FXML
    private TextField searchFirstName;
    @FXML
    private TextField searchLastName;
    @FXML
    private ListView customerListView;
    @FXML
    private TextField customerSearchFirstName;
    @FXML
    private TextField customerSearchLastName;
    @FXML
    private TextField newCustomerFirstName;
    @FXML
    private TextField newCustomerLastName;
    @FXML
    private TextField newCustomerAddress;
    @FXML
    private TextField newCustomerPhoneNumber;
    @FXML
    private Button createNewCustomerButton;
    @FXML
    private ListView customerSearchResultsDisplay;
    @FXML
    private Button customerSearchSubmitButton;
    @FXML
    private TextField newAccountFirstName;
    @FXML
    private TextField newAccountLastName;
    @FXML
    private TextField newAccountAddress;
    @FXML
    private TextField newAccountPhoneNumber;
    @FXML
    private TextField newAccountInitalDeposit;
    @FXML
    private TextField newAccountInterestRate;
    @FXML
    private Button newAccountSubmitButton;
    @FXML
    private TextField yearToDateInterest;

    private CustomerManagement customerManagement;
    private Account account;

    private Customer customer;

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    public void initialize() {
        account = new Account();
        accountBalanceDisplay.setEditable(false);
        accountInterestRateDisplay.setEditable(false);
        accountIdDisplay.setEditable(false);
        customerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> getSelectedCustomer());
    }

    /**
     * This method is called when the user clicks the "Search" button
     * It will search for customers with the given first and last name
     * and display the results in the ListView
     */
    public void getMatchingCustomers() {
        String firstName = searchFirstName.getText();
        String lastName = searchLastName.getText();
        if (firstName.isEmpty() || lastName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Search");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a first and last name to search for.");
            alert.showAndWait();
            return;
        }
        CustomerManagement customerManagement = new CustomerManagement();
        List<Customer> matchList = customerManagement.searchCustomersByName(firstName, lastName);
        if (matchList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Search");
            alert.setHeaderText(null);
            alert.setContentText(
                    "No customers found with the given name.\nMase sure the name is spelled correctly and in the proper case.");
            alert.showAndWait();
            return;
        }
        ObservableList<Customer> observableList = FXCollections.observableList(matchList);
        customerListView.setItems(observableList);
    }

    /**
     * This method is called when the user clicks a customer in the ListView
     * It will display the customer's account information
     * 
     */
    public void getSelectedCustomer() {
        Customer selectedCustomer = (Customer) customerListView.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            return;
        }
        customer = selectedCustomer;
        int accountNumber = customer.getAccountId();
        double balance = DatabaseUtil.getAccountBalance(accountNumber);
        String formattedBalance = String.format("$%.2f", balance);
        accountBalanceDisplay.setText(formattedBalance);
        double interestRate = DatabaseUtil.getAccountInterestRate(accountNumber);
        String formattedInterestRate = String.format("%.2f%%", interestRate * 100);
        accountInterestRateDisplay.setText(formattedInterestRate);
        accountIdDisplay.setText(String.valueOf(accountNumber));
        Account customerAccount = DatabaseUtil.createAccountFromId(accountNumber);
        double yearlyInterest = AccountOperations.calculateTotalInterestForYear(customerAccount);
        yearToDateInterest.setText(String.format("$%.2f", yearlyInterest));
    }

    /**
     * This method is used to edit the customer's profile
     */
    public void editCustomerData() {
        int customerId = customer.getId();
        String firstName = updateFirstName.getText();
        String lastName = updateLastName.getText();
        String address = updateAddress.getText();
        String phoneNumber = updatePhoneNumber.getText();
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Update");
            alert.setHeaderText(null);
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
            return;
        }
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.editCustomerProfile(customerId, firstName, lastName, address, phoneNumber);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer Update");
        alert.setHeaderText(null);
        alert.setContentText("Customer information updated successfully.");
        alert.showAndWait();
        updateFirstName.clear();
        updateLastName.clear();
        updateAddress.clear();
        updatePhoneNumber.clear();
        getMatchingCustomers();
    }

    /**
     * This method is used to credit the customer's account
     */
    public void creditAccount() {
        if (customer == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deposit");
            alert.setHeaderText(null);
            alert.setContentText("Please select a customer.");
            alert.showAndWait();
            return;
        }
        if (DepositAmount.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deposit");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an amount to deposit.");
            alert.showAndWait();
            return;
        }
        double amount = tryParseDouble(DepositAmount.getText());
        if (amount <= 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deposit");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a positive amount to deposit.");
            alert.showAndWait();
            return;
        }
         int accountId = customer.getAccountId();
        Account account = DatabaseUtil.createAccountFromId(accountId);
        AccountOperations.depositFunds(account, amount);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deposit");
        alert.setHeaderText(null);
        alert.setContentText("Deposit successful.");
        alert.showAndWait();
        DepositAmount.clear();
        getSelectedCustomer();
    }

    /**
     * This method is used to withdraw from the customer's account
     */
    public void debitAccount() {
        if (customer == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Withdrawal");
            alert.setHeaderText(null);
            alert.setContentText("Please select a customer.");
            alert.showAndWait();
            return;
        }
        if (WithdrawAmount.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Withdrawal");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an amount to withdraw.");
            alert.showAndWait();
            return;
        }
        double amount = tryParseDouble(WithdrawAmount.getText());
        if (amount <= 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Withdrawal");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a positive amount to withdraw.");
            alert.showAndWait();
            return;
        }
        int accountId = customer.getAccountId();
        Account account = DatabaseUtil.createAccountFromId(accountId);
        AccountOperations.withdrawFunds(account, amount);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Withdrawal");
        alert.setHeaderText(null);
        alert.setContentText("Withdrawal successful.");
        alert.showAndWait();
        WithdrawAmount.clear();
        getSelectedCustomer();
    }

    /**
     * This method is used to View the customer's transaction history
     */
    public void transactionHistory() {
        Date defaultFromDate = java.sql.Date.valueOf(LocalDate.of(2000, 1, 1));
        Date defaultToDate = java.sql.Date.valueOf(LocalDate.now());
        if (customer == null) {
            transactionHistoryTextArea.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Transaction History");
            alert.setHeaderText(null);
            alert.setContentText("Please select a customer.");
            alert.showAndWait();
            return;
        }

        int accountId = customer.getAccountId();
        java.sql.Date fromDateLocal = (transactionFromDate.getValue() != null)
                ? java.sql.Date.valueOf(transactionFromDate.getValue())
                : defaultFromDate;
        java.sql.Date toDateLocal = (transactionToDate.getValue() != null)
                ? java.sql.Date.valueOf(transactionToDate.getValue())
                : defaultToDate;
        String accountStatement = DatabaseUtil.generateAccountStatement(accountId, fromDateLocal, toDateLocal);
        transactionHistoryTextArea.setText(accountStatement);
    }

    /**
     * This method is used to search for a customer for a joint account creation
     * it populates the customer list view with the matching customers
     * so the user can select the customer they want to create a joint account with
     */
    public void getJointAccountHolder() {
        String firstName = customerSearchFirstName.getText();
        String lastName = customerSearchLastName.getText();
        if (firstName.isEmpty() || lastName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Search");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a first and last name to search for.");
            alert.showAndWait();
            return;
        }
        CustomerManagement customerManagement = new CustomerManagement();
        List<Customer> matchList = customerManagement.searchCustomersByName(firstName, lastName);
        if (matchList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Search");
            alert.setHeaderText(null);
            alert.setContentText(
                    "No customers found with the given name.\nMase sure the name is spelled correctly and in the proper case.");
            alert.showAndWait();
            return;
        }
        ObservableList<Customer> observableList = FXCollections.observableList(matchList);
        customerSearchResultsDisplay.setItems(observableList);
    }

    /**
     * Given a selected customer, this method creates a joint account with the customer
     * and displays the new user id upon success
     */
    public void createNewJointAccount() {
        DatabaseUtil dbUtil = new DatabaseUtil();
        Customer selectedCustomer = (Customer) customerSearchResultsDisplay.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Joint Account");
            alert.setHeaderText(null);
            alert.setContentText("Please select a customer.");
            alert.showAndWait();
            return;
        }
        int accountId = selectedCustomer.getAccountId();
        String firstName = newCustomerFirstName.getText();
        String lastName = newCustomerLastName.getText();
        String address = newCustomerAddress.getText();
        String phoneNumber = newCustomerPhoneNumber.getText();
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Joint Account");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all the information for the new customer.");
            alert.showAndWait();
            return;
        }
        int newCustomerId = dbUtil.addCustomer(firstName, lastName, address, phoneNumber, accountId);
        if (newCustomerId == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Joint Account");
            alert.setHeaderText(null);
            alert.setContentText("Error creating new customer.");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Joint Account");
            alert.setHeaderText(null);
            alert.setContentText("New customer created successfully.\nCustomer Id: " + newCustomerId);
            alert.showAndWait();
            newCustomerFirstName.clear();
            newCustomerLastName.clear();
            newCustomerAddress.clear();
            newCustomerPhoneNumber.clear();
            return;
        }
    }

    /**
     * This method is used to create a new customer with an account
     * It creates a new account first so that it can use the same create customer method as the joint account
     * in addition a customer cannot be created without an account number
     */
    public void createCustomerWithAccount(){
        DatabaseUtil dbUtil = new DatabaseUtil();
        String firstName = newAccountFirstName.getText();
        String lastName = newAccountLastName.getText();
        String address = newAccountAddress.getText();
        String phoneNumber = newAccountPhoneNumber.getText();
        int initialDeposit =  tryParseInt(newAccountInitalDeposit.getText());
        double interestRate = tryParseDouble(newAccountInterestRate.getText());
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || initialDeposit == -1 || interestRate == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Account");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all the information for the new customer.");
            alert.showAndWait();
            return;
        }
        int accountID = dbUtil.createNewAccount(initialDeposit, interestRate);
        int newCustomerId = dbUtil.addCustomer(firstName, lastName, address, phoneNumber, accountID);
        if (newCustomerId == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Account");
            alert.setHeaderText(null);
            alert.setContentText("Error creating new customer.");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Account");
            alert.setHeaderText(null);
            alert.setContentText("New customer created successfully.\nCustomer Id: " + newCustomerId + "\nAccount Id: " + accountID);
            alert.showAndWait();
            newAccountFirstName.clear();
            newAccountLastName.clear();
            newAccountAddress.clear();
            newAccountPhoneNumber.clear();
            newAccountInitalDeposit.clear();
            newAccountInterestRate.clear();
            return;
        }
    }

    
    /** 
     * Helper method to validate the input for int values
     * @param value
     * @return int if valid, -1 if not
     */
    private int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Helper method to validate the input for double values
     * @param value
     * @return double if valid, -1 if not
     */
    private double tryParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

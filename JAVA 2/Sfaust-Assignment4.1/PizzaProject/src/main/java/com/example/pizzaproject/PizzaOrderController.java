/*
NAME: Scott Faust
CLASS: 22SP_INFO_1531_WW
ASSIGNMENT: Pizza Order Form
DATE: 4/4/2023
RESOURCES: Lecture slides, JavaFX documentation, StackOverflow, and Oracle documentation
Description: This program is a pizza order form that allows the user to select a pizza size, toppings, and delivery method. The user can then click the Place Order button to see a summary of their order.
 */

package com.example.pizzaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// This class is the controller for the pizza order form
public class PizzaOrderController {
    // Declare the FXML controls
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField phoneNumber;
    @FXML private DatePicker orderDate;
    @FXML private MenuButton orderTime;
    @FXML private RadioButton pickup;
    @FXML private RadioButton delivery;
    @FXML private RadioButton small;
    @FXML private RadioButton medium;
    @FXML private RadioButton large;
    @FXML private CheckBox pepperoni;
    @FXML private CheckBox hamburger;
    @FXML private CheckBox sausage;
    @FXML private CheckBox canadianBacon;
    @FXML private CheckBox chicken;
    @FXML private CheckBox greenPeppers;
    @FXML private CheckBox redPeppers;
    @FXML private CheckBox onions;
    @FXML private CheckBox mushrooms;
    @FXML private CheckBox blackOlives;
    @FXML private TextField total;

    // This method is called when the application is launched
    public void initialize() {
        // Add listener to orderTime menu button
        orderTime.getItems().forEach(item -> item.setOnAction(event -> {
            orderTime.setText(item.getText());
        }));
    }
    public void calculateTotal() {
        try {
            // Calculate the total cost of the pizza order based on the selected options
            double cost = 0.0;
            if (small.isSelected()) {
                cost += 8.99;
            } else if (medium.isSelected()) {
                cost += 11.99;
            } else if (large.isSelected()) {
                cost += 15.99;
            }

            if (pepperoni.isSelected()) {
                cost += 2.05;
            }
            if (hamburger.isSelected()) {
                cost += 1.75;
            }
            if (sausage.isSelected()) {
                cost += 2.05;
            }
            if (canadianBacon.isSelected()) {
                cost += 1.89;
            }
            if (chicken.isSelected()) {
                cost += 2.15;
            }

            if (greenPeppers.isSelected()) {
                cost += 0.99;
            }
            if (redPeppers.isSelected()) {
                cost += 0.99;
            }
            if (onions.isSelected()) {
                cost += 0.99;
            }
            if (mushrooms.isSelected()) {
                cost += 0.99;
            }
            if (blackOlives.isSelected()) {
                cost += 0.99;
            }

            total.setText(String.format("$%.2f", cost));
        } catch (Exception e) {
            ErrorHandler.showError("An error occurred: " + e.getMessage());
        }
    }
    // This method is called when the user clicks the Place Order button
    public void placeOrder() {
        try {
            // Get the values from the form controls
            String firstNameValue = firstName.getText();
            String lastNameValue = lastName.getText();
            String phoneNumberValue = phoneNumber.getText();
            String orderDateValue = orderDate.getValue().toString();
            String orderTimeValue = orderTime.getText();
            String pickupOrDeliveryValue = pickup.isSelected() ? "Pickup" : "Delivery";
            String pizzaSizeValue = "";
            if (small.isSelected()) {
                pizzaSizeValue = "Small (8in)";
            } else if (medium.isSelected()) {
                pizzaSizeValue = "Medium (12in)";
            } else if (large.isSelected()) {
                pizzaSizeValue = "Large (14in)";
            }
            StringBuilder toppingsBuilder = new StringBuilder();
            if (pepperoni.isSelected()) {
                toppingsBuilder.append("Pepperoni, ");
            }
            if (hamburger.isSelected()) {
                toppingsBuilder.append("Hamburger, ");
            }
            if (sausage.isSelected()) {
                toppingsBuilder.append("Sausage, ");
            }
            if (canadianBacon.isSelected()) {
                toppingsBuilder.append("Canadian Bacon, ");
            }
            if (chicken.isSelected()) {
                toppingsBuilder.append("Chicken, ");
            }
            if (greenPeppers.isSelected()) {
                toppingsBuilder.append("Green Peppers, ");
            }
            if (redPeppers.isSelected()) {
                toppingsBuilder.append("Red Peppers, ");
            }
            if (onions.isSelected()) {
                toppingsBuilder.append("Onions, ");
            }
            if (mushrooms.isSelected()) {
                toppingsBuilder.append("Mushrooms, ");
            }
            if (blackOlives.isSelected()) {
                toppingsBuilder.append("Black Olives, ");
            }
            String toppingsValue = toppingsBuilder.toString();
            if (toppingsValue.endsWith(", ")) {
                toppingsValue = toppingsValue.substring(0, toppingsValue.length() - 2);
            }
            calculateTotal();
            String totalValue = total.getText();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Pizza Order");
            alert.setHeaderText(null);
            alert.setContentText(String.format("Name: %s %s%nPhone: %s%nOrder Date: %s%nTime: %s%nPickup/Delivery: %s%nPizza Size: %s%nToppings: %s%nTotal: %s",
                    firstNameValue, lastNameValue, phoneNumberValue, orderDateValue, orderTimeValue, pickupOrDeliveryValue, pizzaSizeValue, toppingsValue, totalValue));
            alert.showAndWait();
            clearForm();
        } catch (Exception e) {
            ErrorHandler.showError("An error occurred: " + e.getMessage());
        }
    }
    // This method is called when the user clicks the Exit button
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }
    // This method is called after the order is placed to clear the form
    public void clearForm() {
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        orderDate.setValue(null);
        orderTime.setText("Select Time");
        pickup.setSelected(true);
        small.setSelected(true);
        pepperoni.setSelected(false);
        hamburger.setSelected(false);
        sausage.setSelected(false);
        canadianBacon.setSelected(false);
        chicken.setSelected(false);
        greenPeppers.setSelected(false);
        redPeppers.setSelected(false);
        onions.setSelected(false);
        mushrooms.setSelected(false);
        blackOlives.setSelected(false);
        total.setText("");
    }
}

/*
NAME: Scott Faust
CLASS: 22SP_INFO_1531_WW
ASSIGNMENT: Pizza Order Form
DATE: 4/4/2023
RESOURCES: Lecture slides, JavaFX documentation, StackOverflow, and Oracle documentation
Description: This program is a pizza order form that allows the user to select a pizza size, toppings, and delivery method. The user can then click the Place Order button to see a summary of their order.
 */

package com.example.pizzaproject;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


// This class is used to display error messages to the user
public class ErrorHandler {
    public static void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

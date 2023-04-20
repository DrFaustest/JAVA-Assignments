/*
NAME: Scott Faust
CLASS: 22SP_INFO_1531_WW
ASSIGNMENT: Pizza Order Form
DATE: 4/4/2023
RESOURCES: Lecture slides, JavaFX documentation, StackOverflow, and Oracle documentation
Description: This program is a pizza order form that allows the user to select a pizza size, toppings, and delivery method. The user can then click the Place Order button to see a summary of their order.
 */

package com.example.pizzaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


// This class is the main entry point for the application
public class PizzaOrderApp extends Application {

    // This method is called when the application is launched
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pizza.fxml"));
        PizzaOrderController controller = new PizzaOrderController();
        fxmlLoader.setController(controller);
        Scene scene;
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Pizza Order Form");
        stage.show();
    }

    // This method is called when the application is launched
    public static void main(String[] args) {
        launch(args);
    }
}

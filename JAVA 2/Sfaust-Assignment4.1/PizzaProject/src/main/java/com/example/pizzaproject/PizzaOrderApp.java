package com.example.pizzaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PizzaApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("pizza.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pizza Order Form");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package com.example.bankingsystem;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainView {

    public MainView(Stage stage) {
        AccountView accountView = new AccountView();
        CustomerView customerView = new CustomerView();

        TabPane tabPane = new TabPane();

        Tab accountTab = new Tab("Accounts", accountView);
        Tab customerTab = new Tab("Customers", customerView);

        tabPane.getTabs().addAll(accountTab, customerTab);

        stage.setScene(new Scene(tabPane, 400, 300));
        stage.show();
    }
}


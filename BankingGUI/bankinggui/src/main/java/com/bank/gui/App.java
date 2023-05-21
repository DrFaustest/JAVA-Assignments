package com.bank.gui;
/**
 * Main App class
 * Description: This is the main class for the bankinggui application.  It is the entry point for the application.
 * The application is a JavaFX application that uses a SQlite database to store customer, account, and transaction information.
 * The main window presents the user with the inital search screen.  The user can search for a customer by first and last name.
 * From there a list of matching customers is displayed.  The user can select a customer from the list and view the customer's
 * account information from one of the two drop down menus.
 * 
 * The first dropdown is the account managment window. From here a user is presented basic account information including 
 * the account number, balance, and interest rate. A tab pane is used to preform transactions on the account.  The user can
 * deposit or withdraw funds from the account and see the intrest for the account from the begingin of the year.
 * The transaction window provides 2 date pickers to select a date range. if no date range is selected the default is the
 * January 1st of 2000 to the current date. Once the view transactions bunnon is pressed all transactions for the selected 
 * customers account id within the date range are displayed.
 * 
 * The second dropdown is the customer managment window.  From here the user can edit the currently selecter customer's
 * first name, last name, address, and phone number. (I wrote the functionality for account deletion but didnt add it to the GUI)
 * 
 * The application also allows for the creation of new customers and accounts.  the user has the option to create a joint account with a pre-existing customer
 * or create a new customer with a new account.
 * 
 * @author Scott Faust
 * @version 1.0 - 2023-05-20
 *
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Main"), 750, 615);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}

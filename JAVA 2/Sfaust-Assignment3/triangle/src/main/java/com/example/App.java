/*Name: Scott Faust
 * Class: 22_SP_1531_WW Java 2
 * Date: 3/27/2023
 * Resources: Class lecture videos, Murach Java Programming, and the Java API, 
 * JavaFX API, and Oracle documentation.
 * 
 * Description: A JavaFX application that calculates the hypotenuse of a triangle.
 */

package com.example;

// import statements
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private TextField sideATextField;
    private TextField sideBTextField;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {

        // Set the title for the stage
        primaryStage.setTitle("Hypotenuse Calculator");

        // Create the title text
        Text titleText = new Text("Hypotenuse Calculator");
        titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 48));
        titleText.setUnderline(true);

        // Create the input grid pane
        GridPane inputGridPane = new GridPane();
        inputGridPane.setAlignment(Pos.CENTER);
        inputGridPane.setHgap(10);
        inputGridPane.setVgap(10);
        inputGridPane.setPadding(new Insets(25, 25, 25, 25));

        // Create the side A label and text field
        Label sideALabel = new Label("Side A:");
        sideATextField = new TextField();
        sideATextField.setPromptText("Enter a number");

        // Create the side B label and text field
        Label sideBLabel = new Label("Side B:");
        sideBTextField = new TextField();
        sideBTextField.setPromptText("Enter a number");

        // Create the side C label and calculate button
        Label sideCLabel = new Label("Side C:");
        Button calculateButton = new Button("Calculate");
        // this is the method that is called when the user clicks the calculate button
        // the method gets the values from the sideATextField and sideBTextField
        // and uses them to calculate the value of sideC
        calculateButton.setOnAction(event -> {
            // the try block attempts to parse the text from the text fields into doubles
            // if the text is not a number, then an exception will be thrown and the catch block will be executed
            try {
                // parse the text from the text fields into doubles
                double sideA = Double.parseDouble(sideATextField.getText());
                double sideB = Double.parseDouble(sideBTextField.getText());
                // calculate the value of sideC
                double sideC = Math.sqrt(sideA * sideA + sideB * sideB);
                // set the text of the result label to the value of sideC
                resultLabel.setText(String.format("Side C: %.2f", sideC));
            // the catch block will be executed if there is an exception
            } catch (NumberFormatException e) {
                // create an empty error message
                String errorMessage = "";
                // if the text from the sideA text field is not a number, then add an error message to the error message
                if (!sideATextField.getText().matches("-?\\d+(\\.\\d+)?")) {
                    errorMessage += "Invalid input for Side A. ";
                }
                // if the text from the sideB text field is not a number, then add an error message to the error message
                if (!sideBTextField.getText().matches("-?\\d+(\\.\\d+)?")) {
                    errorMessage += "Invalid input for Side B. ";
                }
                // set the text of the result label to the error message
                resultLabel.setText(errorMessage.trim());
            }
        });

        
        

        // Create the result label
        resultLabel = new Label("Side C:");
        resultLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        // Create the reset button
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
            sideATextField.setText("");
            sideBTextField.setText("");
            resultLabel.setText("Side C:");
        });

        // Create the exit button
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event2 -> {
            System.exit(0);
        });

        // Add the controls to the input grid pane
        inputGridPane.add(sideALabel, 0, 0); // column 0, row 0 (top left) Label Side A
        inputGridPane.add(sideATextField, 1, 0); // column 1, row 0 (top right) Text Field Side A
        inputGridPane.add(sideBLabel, 0, 1); // column 0, row 1 (middle left) Label Side B
        inputGridPane.add(sideBTextField, 1, 1); // column 1, row 1 (middle right) Text Field Side B
        inputGridPane.add(sideCLabel, 0, 2); // column 0, row 2 (bottom left) Label Side C
        inputGridPane.add(calculateButton, 1, 2); // column 1, row 2 (bottom right) Button Calculate
        inputGridPane.add(resultLabel, 1, 3); // column 1, row 3 (bottom right) Label Result
        inputGridPane.add(resetButton, 0, 4); // column 0, row 4 (bottom left) Button Reset
        inputGridPane.add(exitButton, 1, 4); // column 1, row 4 (bottom right) Button Exit

        // Create the main layout container
        VBox mainVBox = new VBox(20);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(20));
        mainVBox.getChildren().addAll(titleText, inputGridPane);

        // Create the scene and show the stage
        Scene scene = new Scene(mainVBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
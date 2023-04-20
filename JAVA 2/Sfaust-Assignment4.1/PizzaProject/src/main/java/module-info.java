module com.example.pizzaproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pizzaproject to javafx.fxml;
    exports com.example.pizzaproject;
}
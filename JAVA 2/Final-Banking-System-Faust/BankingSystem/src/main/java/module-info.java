module com.example.bankingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bankingsystem to javafx.fxml;
    exports com.example.bankingsystem;
}
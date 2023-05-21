module com.bank.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.bank.gui to javafx.fxml;
    exports com.bank.gui;
}

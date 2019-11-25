module com.hairplay.hairbase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.hairplay.hairbase to javafx.fxml;
    exports com.hairplay.hairbase;
}
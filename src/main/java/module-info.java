module org.example.cheepskies {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.cheepskies to javafx.fxml;
    exports org.example.cheepskies;
}
module org.example.cheepskies {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.cheepskies to javafx.fxml;
    exports org.example.cheepskies;
}
package org.cheepskies.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.cheepskiesdb.DatabaseConnector;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SearchPageController implements Initializable {

    @FXML
    private TextField arrivalLocationTextBox;

    @FXML
    private TableColumn<Flight, String> arrivallocation;

    @FXML
    private Label arriveLocationLabel;

    @FXML
    private Label departDateLabel;

    @FXML
    private TextField departLocTextBox;

    @FXML
    private TextField departureDateTextBox;

    @FXML
    private Label departureLocLabel;

    @FXML
    private TableColumn<Flight, String> departuredate;

    @FXML
    private TableColumn<Flight, String> departurelocation;

    @FXML
    private Label flightDurLabel;

    @FXML
    private TextField flightDurTextBox;

    @FXML
    private Label flightIdLabel;

    @FXML
    private TextField flightIdTextBox;

    @FXML
    private TableColumn<Flight, String> flightduration;

    @FXML
    private TableColumn<Flight, String> flightid;

    @FXML
    private TableColumn<Flight, String> price;

    @FXML
    private Label priceLabel;

    @FXML
    private TextField priceTextBox;

    @FXML
    private TableView<Flight> tableView;

    @FXML
    private Button searchButton;

    @FXML
    void removeFlightFromCustomer(MouseEvent event) {

    }

    @FXML
    void returnToMainMenu(MouseEvent event) {

    }

    @FXML
    void searchFlights(MouseEvent event) {

    }

//    ObservableList<Flight> list = FXCollections.observableArrayList(
//            new Flight()
//    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        arrivallocation.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalLocation"));
        departurelocation.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureLocation"));
        flightid.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightID"));
        flightduration.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightDuration"));
        departuredate.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureDate"));
        price.setCellValueFactory(new PropertyValueFactory<Flight, String>("price"));

    }

//    public SearchPageController() {
//
//        DatabaseConnector db = new DatabaseConnector();
//
//
//
//    }
}

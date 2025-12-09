package org.cheepskies.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.cheepskies.common.ValueObject;
import org.cheepskiesdb.DatabaseConnector;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private ObservableList<Flight> allFlights;

    @FXML
    private TextField departureTimeTextbox;

    @FXML
    private TextField arrivalTimeTextbox;

    @FXML
    private Button adminAddFlight;

    @FXML
    private Label adminOnlyLabel;

    @FXML
    private Button adminRemoveFlightSt;

    @FXML
    private Button adminSearchAllFlights;

    @FXML
    private Button adminUpdateFlight;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField arrivalLocationTextBox;

    @FXML
    private TableColumn<Flight, String> arrivallocation;

    @FXML
    private TableColumn<Flight, String> arrivaltime;

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
    private TableColumn<Flight, String> departuretime;

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
    private TableColumn<Flight, Integer> flightid;

    @FXML
    private TableColumn<Flight, String> price;

    @FXML
    private Label priceLabel;

    @FXML
    private TextField priceTextBox;

    @FXML
    private Text statusLabel;

    @FXML
    private TableView<Flight> tableView;

    @FXML
    private Button toMainMenuButton;

    private int currentUserId;

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
        System.out.println("DEBUG: AdminController received userId = " + userId);
    }

    @FXML
    void returnToMainMenu(MouseEvent event) {

        try {
            //FXMLLoader get access of the controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gui/cheepskies/main-page.fxml"));
            Parent newPage = loader.load();

            // Get the main controller and set the user ID
            MainController mainController = loader.getController();
            mainController.setCurrentUser(currentUserId); // Restore the user ID            // Getting the current stage
            Stage stage = (Stage) toMainMenuButton.getScene().getWindow();

            // Setting the new scene
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("AdminController initialize started...");

        try {


            flightid.setCellValueFactory(new PropertyValueFactory<>("flightId"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            departuredate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
            departurelocation.setCellValueFactory(new PropertyValueFactory<>("departureLocation"));
            arrivallocation.setCellValueFactory(new PropertyValueFactory<>("arrivalLocation"));
            flightduration.setCellValueFactory(new PropertyValueFactory<>("flightDuration"));
            departuretime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
            arrivaltime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

            allFlights = FXCollections.observableArrayList();

            loadAllFlights();

            System.out.println("AdminController initialize completed.");

        } catch (Exception e) {
            System.out.println("Error while retrieving admin flight table: " + e.getMessage());
        }
    }

    @FXML
    void refreshTable(MouseEvent event) {
        loadAllFlights();
    }

    @FXML
    void addFlightClick(MouseEvent event) {

        // grabs textbox content
        String departure = departLocTextBox.getText();
        String arrival = arrivalLocationTextBox.getText();
        String duration = flightDurTextBox.getText();
        String date = departureDateTextBox.getText();
        String priceGrab = priceTextBox.getText();
        String departureTime = departureTimeTextbox.getText();
        String arrivalTime = arrivalTimeTextbox.getText();

        // checks if any boxes are empty
        if (departure.isEmpty() || arrival.isEmpty() || duration.isEmpty() || date.isEmpty() ||
                priceGrab.isEmpty() || departureTime.isEmpty() || arrivalTime.isEmpty()) {

            statusLabel.setText("Missing fields");
            return;
        }

        // format checks
        if (!isValidTime(departureTime)) {
            statusLabel.setText("Invalid departure time format (hh:mm)");
            return;
        }

        if (!isValidTime(arrivalTime)) {
            statusLabel.setText("Invalid arrival time format (hh:mm)");
            return;
        }

        if (!isValidDate(date)) {
            statusLabel.setText("Invalid date format (mm-dd-yyyy)");
            return;
        }

        double price;

        // parses double from String
        try {
            price = Double.parseDouble(priceGrab);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid price format.");
            return;
        }

        ValueObject vo = new ValueObject();
        vo.setAction("adminAddFlight");

        Flight flight = vo.getFlight();

        // setters for flight object
        flight.setDepartureLocation(departure);
        flight.setArrivalLocation(arrival);
        flight.setFlightDuration(duration);
        flight.setDepartureDate(date);
        flight.setPrice(price);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);

        // passing to facade
        try {
            Facade.process(vo);

            // operation result check
            if (vo.operationResult) {
                statusLabel.setText("Flight added successfully");
                loadAllFlights();
            } else {
                statusLabel.setText("Failed to add flight");
            }

        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    void removeFlightClickSt(MouseEvent event) {

        // flight object of selected flight
        Flight selected = tableView.getSelectionModel().getSelectedItem();

        // selection check
        if (selected == null) {
            statusLabel.setText("No flight selected.");
            return;
        }

        // value object block
        ValueObject vo = new ValueObject();
        vo.setAction("adminRemoveFlight");
        vo.setFlight(selected);

        // passing to facade
        try {
            Facade.process(vo);

            // operation result check
            if (vo.operationResult) {
                statusLabel.setText("Flight removed.");
                loadAllFlights();
            } else {
                statusLabel.setText("Failed to remove.");
            }
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    void updateFlightClick(MouseEvent event) {

        // flight object of selected flight
        Flight selected = tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            statusLabel.setText("No flight selected.");
            return;
        }

        // grabs textbox content
        String departure = departLocTextBox.getText();
        String arrival = arrivalLocationTextBox.getText();
        String duration = flightDurTextBox.getText();
        String date = departureDateTextBox.getText();
        String priceGrab = priceTextBox.getText();
        String departureTime = departureTimeTextbox.getText();
        String arrivalTime = arrivalTimeTextbox.getText();

        // checks if user inputted departure time, then checks format
        if (!departureTime.isEmpty()) {
            if (!isValidTime(departureTime)) {
                statusLabel.setText("Invalid departure time format (hh:mm)");
                return;
            }
            selected.setDepartureTime(departureTime);
        }

        // checks if user inputted arrival time, then checks format
        if (!arrivalTime.isEmpty()) {
            if (!isValidTime(arrivalTime)) {
                statusLabel.setText("Invalid arrival time format (hh:mm)");
                return;
            }
            selected.setArrivalTime(arrivalTime);
        }

        // checks if user inputted date, then checks format
        if (!date.isEmpty()) {
            if (!isValidDate(date)) {
                statusLabel.setText("Invalid date format (mm-dd-yyyy)");
                return;
            }
            selected.setDepartureDate(date);
        }

        // content checks
        if (!departure.isEmpty()) selected.setDepartureLocation(departure);
        if (!arrival.isEmpty()) selected.setArrivalLocation(arrival);
        if (!duration.isEmpty()) selected.setFlightDuration(duration);

        // checks price, then parses double, checking for proper price format
        if (!priceGrab.isEmpty()) {
            try {
                selected.setPrice(Double.parseDouble(priceGrab));
            } catch (NumberFormatException e) {
                statusLabel.setText("Invalid price format.");
                return;
            }
        }

        // vo block
        ValueObject vo = new ValueObject();
        vo.setFlight(selected);
        vo.setAction("adminUpdateFlight");

        // passes to facade
        try {
            Facade.process(vo);
            if (vo.operationResult) {
                statusLabel.setText("Flight updated successfully.");
                loadAllFlights();
            } else {
                statusLabel.setText("Failed to update flight.");
            }
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    //searching flights based on textboxes in UI
    @FXML
    void searchFlightsClick(MouseEvent event) {
        try {
            // Get user input from text fields
            String flightId = flightIdTextBox.getText().trim();
            String departLoc = departLocTextBox.getText().trim();
            String arrivalLoc = arrivalLocationTextBox.getText().trim();
            String departDate = departureDateTextBox.getText().trim();
            String flightDur = flightDurTextBox.getText().trim();
            String priceStr = priceTextBox.getText().trim();

            // Build the SQL query dynamically with placeholders
            StringBuilder query = new StringBuilder("SELECT * FROM flights WHERE 1=1");
            List<String> parameters = new ArrayList<>();

            //Dynamically builds WHERE clause, if not empty pull text from fields to include in query. If empty where 1=1 (no filter aka. *)
            if (!flightId.isEmpty()) {
                query.append(" AND flightId = ?");
                parameters.add(flightId);
            }
            if (!departLoc.isEmpty()) {
                query.append(" AND departureLocation = ?");
                parameters.add(departLoc);
            }
            if (!arrivalLoc.isEmpty()) {
                query.append(" AND arrivalLocation = ?");
                parameters.add(arrivalLoc);
            }
            if (!departDate.isEmpty()) {
                query.append(" AND departureDate = ?");
                parameters.add(departDate);
            }
            if (!flightDur.isEmpty()) {
                query.append(" AND flightDuration = ?");
                parameters.add(flightDur);
            }
            if (!priceStr.isEmpty()) {
                query.append(" AND price = ?");
                parameters.add(priceStr);
            }


            DatabaseConnector db = new DatabaseConnector();

            // Execute the query with parameters
            ResultSet rs = db.executePreparedQuery(query.toString(), parameters);

            // Create a list to hold the results
            ObservableList<Flight> flightList = FXCollections.observableArrayList();

            // Loop through results and create Flight objects
            while (rs.next()) {
                Flight flight = new Flight(
                        rs.getString("arrivalLocation"),
                        rs.getString("departureLocation"),
                        rs.getInt("flightId"),
                        rs.getDouble("price"),
                        rs.getString("departureDate"),
                        rs.getString("flightDuration")
                );
                flightList.add(flight);
            }

            // Display results in the table
            tableView.setItems(flightList);

        } catch (Exception e) {
            e.printStackTrace();
            // Consider showing an error alert to the user
        }
    }

    //displays all flights in flight table when opening admin page
    private void loadAllFlights() {
        allFlights.clear(); //Prevents record duplication
        String query = "SELECT * FROM flights";

        System.out.println("Loading all flights from database..."); //debugging stuff

        //Establish DB connection and queries
        try (Connection conn = DatabaseConnector.dbConnect();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery();) {

            int count = 0; //debugging

            //while next result is true, create flight object with fetched result
            while (rs.next()) {
                Flight flight = new Flight(
                        rs.getString("departurelocation"),
                        rs.getString("departuretime"),
                        rs.getString("arrivallocation"),
                        rs.getString("arrivaltime"),
                        rs.getString("flightduration"),
                        rs.getString("departuredate"),
                        rs.getDouble("price")
                );
                flight.setFlightId(rs.getInt("flightid"));
                allFlights.add(flight); //add flights to observable list

                //debugging stuff
                count++;
                System.out.println("Loaded flight: " + flight.getFlightId() + " - " +
                        flight.getDepartureLocation() + " to " + flight.getArrivalLocation());
            }

            System.out.println("Total flights loaded: " + count); //debugging stuff
            tableView.setItems(allFlights);

        } catch (SQLException e) {
            System.out.println("Error loading flights: " + e.getMessage());
        }
    }

    //helper methods for format validation
    private boolean isValidTime(String time) {

        if (time == null || time.length() != 5) return false;
        if (time.charAt(2) != ':') return false;

        char h1 = time.charAt(0);
        char h2 = time.charAt(1);
        char m1 = time.charAt(3);
        char m2 = time.charAt(4);

        if (!Character.isDigit(h1) || !Character.isDigit(h2)) return false;
        if (!Character.isDigit(m1) || !Character.isDigit(m2)) return false;

        int hour = (h1 - '0') * 10 + (h2 - '0');
        int minute = (m1 - '0') * 10 + (m2 - '0');

        if (hour < 0 || hour > 23) return false;
        if (minute < 0 || minute > 59) return false;

        return true;
    }

    //helper methods for format validation
    private boolean isValidDate(String date) {
        if (date == null || date.length() != 10) return false;
        if (date.charAt(2) != '-' || date.charAt(5) != '-') return false;

        char m1 = date.charAt(0);
        char m2 = date.charAt(1);
        char d1 = date.charAt(3);
        char d2 = date.charAt(4);

        if (!Character.isDigit(m1) || !Character.isDigit(m2)) return false;
        if (!Character.isDigit(d1) || !Character.isDigit(d2)) return false;

        for (int i = 6; i < 10; i++) {
            if (!Character.isDigit(date.charAt(i))) return false;
        }

        int month = (m1 - '0') * 10 + (m2 - '0');
        int day = (d1 - '0') * 10 + (d2 - '0');
        int year = Integer.parseInt(date.substring(6, 10));

        if (month < 1 || month > 12) return false;
        if (day < 1 || day > 31) return false;
        if (year <= 0) return false;

        return true;

    }
}




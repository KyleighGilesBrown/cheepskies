package org.cheepskies.ui;

public class Flight {
    private String departureLocation;
    private String departureTime;
    private String arrivalLocation;
    private String arrivalTime;
    private String flightDuration;
    private String arrivalDate;
    private String departureDate;
    private String price;
    public int flightId;



    public int getFlightId(){
        return flightId;
    }

    public void setFlightId(int flightId){
        this.flightId = flightId;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(String flightDuration) {
        this.flightDuration = flightDuration;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String departureDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Flight(String departureLocation, String departureTime, String arrivalLocation,
                  String arrivalTime, String flightDuration, String departureDate, String price) {
        this.departureLocation = departureLocation;
        this.departureTime = departureTime;
        this.arrivalLocation = arrivalLocation;
        this.arrivalTime = arrivalTime;
        this.flightDuration = flightDuration;
        this.departureDate = departureDate;
        this.price = price;
    }

    public Flight() {
    }
}

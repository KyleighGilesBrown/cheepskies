package org.cheepskiesexceptions;

public class AddFlightRecordDuplicationException extends FlightSchedulingException {
    private String flightId;

    public AddFlightRecordDuplicationException(String message) {
        super(message);
    }

    public AddFlightRecordDuplicationException(String message, String flightId) {
        super(message);
        this.flightId = flightId;
    }

    public AddFlightRecordDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getFlightId() {
        return flightId;
    }
}

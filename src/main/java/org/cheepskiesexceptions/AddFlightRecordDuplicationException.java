package org.cheepskiesexceptions;

public class AddFlightRecordDuplicationException extends FlightSchedulingException {
    private String flightid;

    public AddFlightRecordDuplicationException(String message) {
        super(message);
    }

    public AddFlightRecordDuplicationException(String message, String flightId) {
        super(message);
        this.flightid = flightid;
    }

    public AddFlightRecordDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getFlightId() {
        return flightid;
    }
}

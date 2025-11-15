package org.cheepskiesexceptions;

public class AddToFlightListException extends FlightSchedulingException {
    public AddToFlightListException(String message) {
        super(message);
    }

    public AddToFlightListException(String message, Throwable cause) {
        super(message, cause);
    }
}

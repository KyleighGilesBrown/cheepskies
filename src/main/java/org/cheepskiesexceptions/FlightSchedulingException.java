package org.cheepskiesexceptions;

public class FlightSchedulingException extends Exception{
    public FlightSchedulingException(String message) {
        super(message);
    }

    public FlightSchedulingException(String message, Throwable cause) {
        super(message, cause);
    }
}

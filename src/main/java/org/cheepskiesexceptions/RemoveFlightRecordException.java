package org.cheepskiesexceptions;

public class RemoveFlightRecordException extends FlightSchedulingException {
    public RemoveFlightRecordException(String message) {
        super(message);
    }

    public RemoveFlightRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}

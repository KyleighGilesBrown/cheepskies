package org.cheepskiesexceptions;

public class RemoveFlightListofRecordException extends FlightSchedulingException {
    public RemoveFlightListofRecordException(String message) {
        super(message);
    }

    public RemoveFlightListofRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}

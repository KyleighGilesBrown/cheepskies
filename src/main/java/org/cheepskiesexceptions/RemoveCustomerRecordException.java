package org.cheepskiesexceptions;

public class RemoveCustomerRecordException extends FlightSchedulingException {
    public RemoveCustomerRecordException(String message) {
        super(message);
    }

    public RemoveCustomerRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}

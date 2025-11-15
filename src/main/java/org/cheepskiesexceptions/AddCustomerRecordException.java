package org.cheepskiesexceptions;

public class AddCustomerRecordException extends FlightSchedulingException {
    public AddCustomerRecordException(String message) {
        super(message);
    }

    public AddCustomerRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}

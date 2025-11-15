package org.cheepskiesexceptions;

public class GetCustomerRecordException extends FlightSchedulingException {
    public GetCustomerRecordException(String message) {
        super(message);
    }

    public GetCustomerRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}

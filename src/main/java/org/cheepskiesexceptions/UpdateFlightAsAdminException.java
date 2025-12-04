package org.cheepskiesexceptions;

public class UpdateFlightAsAdminException extends Exception {
    public UpdateFlightAsAdminException(String message) {
        super(message);
    }

    public UpdateFlightAsAdminException(String message, Throwable cause) {
        super(message, cause);
    }
}

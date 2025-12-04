
package org.cheepskiesexceptions;

public class RemoveFlightAsAdminException extends Exception {
    public RemoveFlightAsAdminException(String message) {
        super(message);
    }

    public RemoveFlightAsAdminException(String message, Throwable cause) {
        super(message, cause);
    }
}

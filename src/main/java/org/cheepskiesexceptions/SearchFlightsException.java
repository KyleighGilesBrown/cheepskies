package org.cheepskiesexceptions;

public class SearchFlightsException extends Exception {
    public SearchFlightsException(String message) {
        super(message);
    }

    public SearchFlightsException(String message, Throwable cause) {
        super(message, cause);
    }
}


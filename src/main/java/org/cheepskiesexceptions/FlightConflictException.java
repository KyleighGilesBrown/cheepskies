package org.cheepskiesexceptions;

public class FlightConflictException extends Exception{

        public FlightConflictException(String message) {
            super(message);
        }

        public FlightConflictException(String message, Throwable cause) {
            super(message, cause);
        }
    }


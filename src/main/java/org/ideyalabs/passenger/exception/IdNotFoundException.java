package org.ideyalabs.passenger.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException() {
        super("Resource Not Found on the Server");

    }
    public IdNotFoundException(String message) {
        super(message);

    }



}


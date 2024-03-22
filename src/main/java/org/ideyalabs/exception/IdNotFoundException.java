package org.ideyalabs.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdNotFoundException extends RuntimeException {



    public IdNotFoundException() {
        super("Resource Not Found on the Server");


    }
    public IdNotFoundException(String message) {
        super(message);


    }

}


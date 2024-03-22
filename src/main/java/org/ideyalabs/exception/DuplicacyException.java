package org.ideyalabs.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicacyException extends  RuntimeException{


    public DuplicacyException(String message){
        super(message);

    }
}

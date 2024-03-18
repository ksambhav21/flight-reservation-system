package org.ideyalabs.passenger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {

    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handler(IdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }




    @ExceptionHandler(DuplicacyException.class)
    public ResponseEntity<String> duplicateValueHandler(DuplicacyException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    //    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<String> expiredJwthandler(ExpiredJwtException e)
//    {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//
//    @ExceptionHandler(MalformedJwtException.class)
//    public ResponseEntity<String> malformedhandler(MalformedJwtException e)
//    {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericExceptionHandler(Exception e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentHandler(MethodArgumentNotValidException e) {
        Map<String,String> response= new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)->
        {
            String fieldName =((FieldError)error).getField();
            String message = error.getDefaultMessage();
            response.put(fieldName,message);

        }
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }



}
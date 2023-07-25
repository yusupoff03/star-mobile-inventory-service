package com.example.sofiyainventoryservice.config;

import com.example.sofiyainventoryservice.exception.DataNotFoundException;
import com.example.sofiyainventoryservice.exception.RequestValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<String> dataNotFoundExceptionHandler(
            DataNotFoundException e){
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(value = {RequestValidationException.class})
    public ResponseEntity<String> requestValidationExceptionHandler(
            RequestValidationException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }




}

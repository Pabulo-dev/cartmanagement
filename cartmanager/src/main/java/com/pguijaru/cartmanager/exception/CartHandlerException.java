package com.pguijaru.cartmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartHandlerException {

    @ExceptionHandler({CartException.class})
    public ResponseEntity<String> cartRequestException(CartException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getErrorMessage());
    }

    @ExceptionHandler({CartNotFoundException.class})
    public ResponseEntity<String> cartNotFoundException(CartNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getErrorMessage());
    }
}

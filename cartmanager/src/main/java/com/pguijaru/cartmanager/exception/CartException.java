package com.pguijaru.cartmanager.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartException extends RuntimeException{
    private final String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
}

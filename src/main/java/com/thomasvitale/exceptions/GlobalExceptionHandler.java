package com.thomasvitale.exceptions;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.security.SignatureException;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(SignatureException.class)
    public void failedToVerifyTokenSignature() {
        System.out.println("");
    }
    
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(FailedToAuthenticateException.class)
    public void failedToAuthenticate() {
    }
    
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public void notFound() {
    }
}

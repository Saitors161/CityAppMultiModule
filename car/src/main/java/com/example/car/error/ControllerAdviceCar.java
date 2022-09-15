package com.example.car.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.net.ConnectException;
import java.util.Date;

@ControllerAdvice
public class ControllerAdviceCar {

    @ExceptionHandler({ConnectException.class})
    protected ResponseEntity<Object> handleConnectErrorEx(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError("The resource is not available at the moment",new Date());
        return new ResponseEntity<>(apiError, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundEx(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage(),new Date());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}

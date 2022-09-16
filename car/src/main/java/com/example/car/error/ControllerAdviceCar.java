package com.example.car.error;

import com.example.common.error.ApiError;
import com.example.common.error.IncorrectDataException;
import com.example.common.error.TimeoutConnectCitizenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.net.ConnectException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class ControllerAdviceCar {

    @ExceptionHandler({ConnectException.class, ResourceAccessException.class})
    protected ResponseEntity<Object> handleConnectErrorEx(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError("The resource is not available at the moment", new Date());
        return new ResponseEntity<>(apiError, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundEx(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage(), new Date());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IncorrectDataException.class})
    protected ResponseEntity<Object> handleIncorrectDataForCarEx(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage(), new Date());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TimeoutConnectCitizenException.class})
    protected ResponseEntity<Object> handleTimeoutForCarEx(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage(), new Date());
        return new ResponseEntity<>(apiError, HttpStatus.REQUEST_TIMEOUT);
    }

}

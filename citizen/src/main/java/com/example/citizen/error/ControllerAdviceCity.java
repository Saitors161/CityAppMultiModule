package com.example.citizen.error;

import com.example.common.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@ControllerAdvice
public class ControllerAdviceCity {

    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundEx(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage(),new Date());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}

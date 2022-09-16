package com.example.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class TimeoutConnectCitizenException extends RuntimeException {
    public TimeoutConnectCitizenException(String message) {
        super(message);
    }
}

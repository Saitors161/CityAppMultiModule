package com.example.citizen.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ApiError {
    private String debugMessage;
    private Date date ;
}
package com.example.common.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CarDto {
    private Integer id;
    private UUID serialNumber;
    private Date registered;
    private String typeCar;
    private Integer citizenId;
}

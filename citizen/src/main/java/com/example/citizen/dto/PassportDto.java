package com.example.citizen.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class PassportDto {
    private Integer id;
    private UUID number;
    private Date dateOfCreated;
}

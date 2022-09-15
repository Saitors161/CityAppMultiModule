package com.example.common.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class PassportDto {
    private Integer id;
    private UUID number;
    private Date dateOfCreated;
}

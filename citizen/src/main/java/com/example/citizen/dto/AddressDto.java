package com.example.citizen.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Integer id;
    private String street;
    private String city;
    private Integer number;
}

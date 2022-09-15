package com.example.common.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Integer id;
    private String street;
    private String city;
    private Integer number;
}

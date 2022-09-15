package com.example.common.dto;

import lombok.Data;

import java.util.Set;
@Data
public class BuildDto {
    private Integer id;
    private Integer square;
    private Set<Integer> owners;
    private AddressDto address;
}

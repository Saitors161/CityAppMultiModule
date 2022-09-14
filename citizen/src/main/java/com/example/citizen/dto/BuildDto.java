package com.example.citizen.dto;

import com.example.citizen.model.Address;
import lombok.Data;

import java.util.Set;
@Data
public class BuildDto {
    private Integer id;
    private Integer square;
    private Set<Integer> owners;
    private Address address;
}

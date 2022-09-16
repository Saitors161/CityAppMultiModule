package com.example.common.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class CitizenDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private PassportDto passport;
    private Set<CarDto> cars;
    private HashSet<BuildDto> builds;
}

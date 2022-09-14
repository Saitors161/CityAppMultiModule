package com.example.citizen.dto;

import com.example.citizen.model.Build;
import com.example.citizen.model.Car;
import com.example.citizen.model.Passport;
import lombok.Data;

import javax.persistence.Column;
import java.util.Set;
@Data
public class CitizenDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private PassportDto passport;
    private Set<CarDto> cars;
    private Set<BuildDto> builds;
}

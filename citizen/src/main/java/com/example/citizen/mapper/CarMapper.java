package com.example.citizen.mapper;

import com.example.citizen.dto.CarDto;
import com.example.citizen.dto.CitizenDto;
import com.example.citizen.model.Car;
import com.example.citizen.model.Citizen;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car toModel(CarDto carDto);
    CarDto toDto(Car car);
}

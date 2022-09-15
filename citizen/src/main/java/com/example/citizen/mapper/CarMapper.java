package com.example.citizen.mapper;

import com.example.citizen.model.Car;
import com.example.common.dto.CarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car toModel(CarDto carDto);
    CarDto toDto(Car car);
}

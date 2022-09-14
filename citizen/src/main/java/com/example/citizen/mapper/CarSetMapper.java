package com.example.citizen.mapper;

import com.example.citizen.dto.CarDto;
import com.example.citizen.model.Car;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public interface CarSetMapper {
    Set<Car> toModel(Set<CarDto> setCarDto);
    Set<CarDto> toDto(Set<Car> setCar);
}

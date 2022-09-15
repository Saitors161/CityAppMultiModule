package com.example.car.mapper;

import com.example.car.dto.CarDto;
import com.example.car.model.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car toModel (CarDto carDto);
    CarDto toDto (Car car);
}

package com.example.car.mapper;

import com.example.car.dto.CarDto;
import com.example.car.model.Car;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public interface CarListMapper {
    List<Car> toModel (List<CarDto> setCarDto);
    List<CarDto> toDto (List<Car> setCar);
}

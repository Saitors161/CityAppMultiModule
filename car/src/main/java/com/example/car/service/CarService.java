package com.example.car.service;

import com.example.car.dto.CarDto;

import java.util.List;
import java.util.Set;

public interface CarService {
    CarDto save (CarDto carDto);
    CarDto update (CarDto carDto);
    void deleteById (Integer id);
    CarDto getById (Integer id);
    List<CarDto> getAll();
}

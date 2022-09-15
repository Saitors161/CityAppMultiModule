package com.example.car.controller;

import com.example.car.dto.CarDto;
import com.example.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CarDto getCarById(@PathVariable Integer id){
        return carService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CarDto> getAllCars(){
        return carService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CarDto createCar(@RequestBody CarDto carDto){
        return carService.save(carDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    CarDto updateCar(@RequestBody CarDto carDto){
        return carService.update(carDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.CREATED)
    void deleteCarById(@PathVariable Integer id){
        carService.deleteById(id);
    }
}

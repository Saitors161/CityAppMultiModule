package com.example.car.service.impl;

import com.example.car.dto.CarDto;
import com.example.car.mapper.CarMapper;
import com.example.car.mapper.CarListMapper;
import com.example.car.model.Car;
import com.example.car.repository.CarRepository;
import com.example.car.service.CarService;
import com.example.car.util.TypeCar;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarListMapper carListMapper;
    private final CarMapper carMapper;
    @Value("${city.citizen-app.citizen-url}")
    private String urlCitizenApp; //todo: maybe need refactoring


    @Override
    public CarDto save(CarDto carDto) {
        Set<String> incorrectData = checkDataForCarDto(carDto);
        if (incorrectData.isEmpty()) {
            Car car = carMapper.toModel(carDto);
            car.setSerialNumber(UUID.randomUUID());
            car.setRegistered(new Date());
            return carMapper.toDto(carRepository.save(car));
        }

        throw new RuntimeException("Incorrect data for car creating: " + incorrectData + ". " +
                "Creating car was cancel");
        //todo: maybe will using common error from citizen app

    }

    private Set<String> checkDataForCarDto(CarDto carDto) {
        Set<String> incorrectData = new HashSet<>();
        checkDataForCitizen(incorrectData, carDto.getCitizenId());
        checkDataForCar(incorrectData, carDto);
        return incorrectData;
    }

    private void checkDataForCar(Set<String> incorrectData, CarDto carDto) {
        if (carDto.getTypeCar() == null || carDto.getTypeCar().equals("")) {
            incorrectData.add("Type car is empty");
        } else {
            boolean typeCarIsCorrect = false;
            for (TypeCar typeCar : TypeCar.values()) {
                if (carDto.getTypeCar().toUpperCase().equals(typeCar.toString())) {
                    typeCarIsCorrect = true;
                    break;
                }
            }
            if (!typeCarIsCorrect) {
                incorrectData.add("Incorrect type of car : " + carDto.getTypeCar() +
                        "Possible types: " + Arrays.toString(TypeCar.values()));
            }
        }
    }

    private void checkDataForCitizen(Set<String> incorrectData, Integer citizenId) {
        if (citizenId == null || citizenId == 0 || !citizenExist(citizenId)) {
            incorrectData.add("Citizen with id " + citizenId + " not found.");
        }
        ;
    }

    private boolean citizenExist(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(urlCitizenApp + "/" + id, String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)){
            return true;
        }
        return false;
    }

    @Override
    public CarDto update(CarDto carDto) {
        Car car = carRepository.findById(carDto.getId()).orElseThrow(()-> new EntityNotFoundException("E"));

        return null;
    }

    @Override
    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDto getById(Integer id) {
        return carMapper.toDto(carRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Car with id " + id + " not found");
        }));
    }

    @Override
    public List<CarDto> getAll() {
        return carListMapper.toDto(carRepository.findAll());
    }
}

package com.example.car.service.impl;

import com.example.car.dto.CarDto;
import com.example.car.mapper.CarListMapper;
import com.example.car.mapper.CarMapper;
import com.example.car.model.Car;
import com.example.car.repository.CarRepository;
import com.example.car.service.CarService;
import com.example.car.service.CitizenService;
import com.example.common.enums.TypeCar;
import com.example.common.error.IncorrectDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.currentThread;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarListMapper carListMapper;
    private final CarMapper carMapper;
    private final CitizenService citizenService;


    @Override
    public CarDto save(CarDto carDto) {
        Set<String> incorrectData = checkDataForCarDtoSave(carDto);
        if (incorrectData.isEmpty()) {
            Car car = carMapper.toModel(carDto);
            car.setSerialNumber(UUID.randomUUID());
            car.setRegistered(new Date());
            return carMapper.toDto(carRepository.save(car));
        }

        throw new IncorrectDataException("Incorrect data for car creating: " + incorrectData + ". " +
                "Creating car was cancel");
        //todo: maybe will using common error from citizen app

    }

    private Set<String> checkDataForCarDtoSave(CarDto carDto) {
        Set<String> incorrectData = ConcurrentHashMap.newKeySet();
        CountDownLatch door = new CountDownLatch(2);
        citizenService.checkDataForCitizenThread(incorrectData, carDto.getCitizenId(), door);
        checkDataForSaveCarThread(incorrectData, carDto, door);

        try {
            door.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return incorrectData;
    }

    private void checkDataForSaveCarThread(Set<String> incorrectData, CarDto carDto, CountDownLatch door) {
        Runnable runCheckDataForCitizen = () -> {
            checkDataForCar(incorrectData, carDto);
            System.out.println("The car was checked. Thread : " + currentThread().getName());
            door.countDown();
        };
        Thread threadCheckDataForCitizen = new Thread(runCheckDataForCitizen);
        threadCheckDataForCitizen.start();
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
                        ". Possible types: " + Arrays.toString(TypeCar.values()));
            }
        }
    }

    @Override
    public CarDto update(CarDto carDto) {
        Set<String> incorrectData = new HashSet<>();
        CountDownLatch door = new CountDownLatch(1);
        citizenService.checkDataForCitizenThread(incorrectData, carDto.getCitizenId(), door);
        Car car = carRepository.findById(carDto.getId()).orElse(null);
        if (car == null) {
            incorrectData.add("Car with id " + carDto.getId() + " not found");
        }
        try {
            door.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (incorrectData.isEmpty()) {
            car.setCitizenId(carDto.getCitizenId());
            car.setRegistered(new Date());
            return carMapper.toDto(carRepository.save(car));
        }
        throw new IncorrectDataException("Incorrect data for car creating: " + incorrectData + ". " +
                "Creating car was cancel");
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

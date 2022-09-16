package com.example.car.service;


import com.example.common.dto.CitizenDto;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public interface CitizenService {
    CitizenDto findById(Integer id);
    void checkDataForCitizen(Set<String> incorrectData, Integer citizenId);
    void checkDataForCitizenThread(Set<String> incorrectData, Integer citizenId, CountDownLatch door);
}

package com.example.car.service;


import com.example.common.dto.CitizenDto;

import java.util.Set;

public interface CitizenService {
    CitizenDto findById(Integer id);
    void checkDataForCitizen(Set<String> incorrectData, Integer citizenId);
}

package com.example.car.service.impl;

import com.example.car.service.CitizenService;
import com.example.common.dto.CitizenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
public class CitizenServiceImpl implements CitizenService {
    @Value("${city.citizen-app.citizen-url}")
    private String urlCitizenApp;

    @Override
    public CitizenDto findById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        try{
            return restTemplate.getForEntity(urlCitizenApp + "/" + id, CitizenDto.class).getBody();
        }catch (HttpClientErrorException e) {
            return null;
        }
    }

    public void checkDataForCitizen(Set<String> incorrectData, Integer citizenId) {
        if (citizenId == null || citizenId == 0 || findById(citizenId) == null) {
            incorrectData.add("Citizen with id " + citizenId + " not found.");
        }
    }
}

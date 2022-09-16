package com.example.car.service.impl;

import com.example.car.service.CitizenService;
import com.example.common.dto.CitizenDto;
import com.example.common.error.TimeoutConnectCitizenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.currentThread;

@Service
public class CitizenServiceImpl implements CitizenService {
    @Value("${city.citizen-app.citizen-url}")
    private String urlCitizenApp;

    @Override
    public CitizenDto findById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(urlCitizenApp + "/" + id, CitizenDto.class).getBody();
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

    public void checkDataForCitizen(Set<String> incorrectData, Integer citizenId) {
        if (citizenId == null || citizenId == 0
                || findById(citizenId) == null) {
            incorrectData.add("Citizen with id " + citizenId + " not found.");
        }
    }

    public void checkDataForCitizenThread(Set<String> incorrectData, Integer citizenId, CountDownLatch door) {
        class CheckCitizen implements Callable<Boolean> {
            @Override
            public Boolean call() throws Exception {
                try {
                    checkDataForCitizen(incorrectData, citizenId);
                    door.countDown();
                } catch (ResourceAccessException e) {
                    return false;
                }
                return true;
            }
        }
        CheckCitizen checkCitizen = new CheckCitizen();
        //todo: maybe need refactoring
        try {
            if (!checkCitizen.call()) {
                throw new TimeoutConnectCitizenException("Identity verification is not possible at this time" +
                        ", try again later. Vehicle registration has been cancelled.");
            }
        } catch (Exception e) {
            throw new TimeoutConnectCitizenException("Identity verification is not possible at this time" +
                    ", try again later. Vehicle registration has been cancelled.");
        }

    }

}

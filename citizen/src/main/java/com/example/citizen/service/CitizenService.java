package com.example.citizen.service;

import com.example.citizen.dto.CitizenDto;
import com.example.citizen.model.Citizen;

import java.util.Set;

public interface CitizenService {
    CitizenDto save(CitizenDto citizenDto);
    CitizenDto update(CitizenDto citizenDto);
    void deleteById(Integer id);
    CitizenDto getById(Integer id);
    Set<CitizenDto> getAll();
 }

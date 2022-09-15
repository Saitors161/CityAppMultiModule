package com.example.citizen.service;


import com.example.citizen.model.Passport;
import com.example.common.dto.PassportDto;

import java.util.Set;

public interface PassportService {
    PassportDto save(PassportDto passportDto);
    Passport save(Passport passport);
    PassportDto update(PassportDto passportDto);
    void deleteById(Integer id);
    PassportDto getById(Integer id);
    Set<PassportDto> getAll();
}

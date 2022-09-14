package com.example.citizen.service.impl;

import com.example.citizen.dto.PassportDto;
import com.example.citizen.error.EntityInCityNotFoundException;
import com.example.citizen.mapper.PassportMapper;
import com.example.citizen.mapper.PassportSetMapper;
import com.example.citizen.model.Passport;
import com.example.citizen.repository.PassportRepository;
import com.example.citizen.service.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;
    private final PassportSetMapper passportSetMapper;

    @Override
    public PassportDto save(PassportDto passportDto) {
        Passport passport = passportMapper.toModel(passportDto);
        return passportMapper.toDto(passportRepository.save(passport));
    }

    @Override
    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }

    @Override
    public PassportDto update(PassportDto passportDto) {
        Passport passport = passportRepository.findById(passportDto.getId()).orElseThrow(()->
                new EntityInCityNotFoundException("passport with id" + passportDto.getId() + " not found"));
        passport.setNumber(passportDto.getNumber());
        return passportMapper.toDto(passportRepository.save(passport));
    }

    @Override
    public void deleteById(Integer id) {
        passportRepository.deleteById(id);
    }

    @Override
    public PassportDto getById(Integer id) {
        Passport passport = passportRepository.findById(id).orElseThrow(()-> new EntityInCityNotFoundException(
                "passport with id" + id + " not found"
        ));
        return passportMapper.toDto(passport);
    }

    @Override
    public Set<PassportDto> getAll() {
        return passportSetMapper.toDto(Set.copyOf(passportRepository.findAll()));
    }
}

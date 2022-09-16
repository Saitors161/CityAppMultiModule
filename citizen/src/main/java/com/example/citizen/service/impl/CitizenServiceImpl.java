package com.example.citizen.service.impl;

import com.example.citizen.mapper.CitizenMapper;
import com.example.citizen.mapper.CitizenSetMapper;
import com.example.citizen.model.Citizen;
import com.example.citizen.model.Passport;
import com.example.citizen.repository.CitizenRepository;
import com.example.citizen.service.CitizenService;
import com.example.citizen.service.PassportService;
import com.example.common.dto.CitizenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;
    private final PassportService passportService;
    private final CitizenMapper citizenMapper;
    private final CitizenSetMapper citizenSetMapper;

    @Override
    @Transactional
    public CitizenDto save(CitizenDto citizenDto) {
        Citizen citizen = citizenMapper.toModel(citizenDto);
        Passport passport = new Passport();
        passport.setNumber(UUID.randomUUID());
        passport.setDateOfCreated(new Date());
        passport = passportService.save(passport);
        citizen.setPassport(passport);
        return citizenMapper.toDto(citizenRepository.save(citizen));
    }

    @Override
    public CitizenDto update(CitizenDto citizenDto) {
        Citizen citizenInDb = citizenRepository.findById(citizenDto.getId()).orElseThrow(()->new EntityNotFoundException("citizen with"));
        Citizen citizen = citizenMapper.toModel(citizenDto);
        citizen.setPassport(citizenInDb.getPassport());
        return citizenMapper.toDto(citizenRepository.save(citizen));
    }

    @Override
    public void deleteById(Integer id) {
        citizenRepository.deleteById(id);
    }

    @Override
    public CitizenDto getById(Integer id) {
        Citizen citizen = citizenRepository.findById(id).orElseThrow(()->new EntityNotFoundException(
                "citizen with id " + id +" not found!"));
        return citizenMapper.toDto(citizen);
    }

    @Override
    public Set<CitizenDto> getAll() {
        return citizenSetMapper.toDto(Set.copyOf(citizenRepository.findAll()));
    }
}

package com.example.citizen.service.impl;

import com.example.citizen.dto.CitizenDto;
import com.example.citizen.dto.PassportDto;
import com.example.citizen.mapper.CitizenMapper;
import com.example.citizen.mapper.CitizenSetMapper;
import com.example.citizen.mapper.CitizenSetMapperImpl;
import com.example.citizen.model.Citizen;
import com.example.citizen.model.Passport;
import com.example.citizen.repository.CitizenRepository;
import com.example.citizen.service.CitizenService;
import com.example.citizen.service.PassportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CitizenServiceTest {
    private CitizenRepository citizenRepository = Mockito.mock(CitizenRepository.class);
    private PassportService passportService = Mockito.mock(PassportService.class);
    private CitizenMapper citizenMapper = Mockito.mock(CitizenMapper.class);
    private CitizenSetMapper citizenSetMapper = Mockito.mock(CitizenSetMapperImpl.class);
    private CitizenService citizenService = new CitizenServiceImpl(citizenRepository,
            passportService,
            citizenMapper,
            citizenSetMapper);

    @Test
    void checkCreateCitizen(){

        Passport passport = getPassportForTest();

        PassportDto passportDto = getPassportDtoForTest(passport);

        Mockito.when(passportService.save(passport)).thenReturn(passport);

        CitizenDto citizenDto = getCitizenDtoForTest(passportDto);


        Citizen citizen = getCitizenForTest(citizenDto,passport);

        Mockito.when(citizenRepository.save(citizen)).thenReturn(citizen);
        Mockito.when(citizenMapper.toModel(citizenDto)).thenReturn(citizen);
        Mockito.when(citizenMapper.toDto(citizen)).thenReturn(citizenDto);

        CitizenDto citizenDtoCheck= citizenService.save(citizenDto);
        verify(citizenRepository, times(1)).save(citizen);
        verify(citizenMapper, times(1)).toDto(citizen);
        citizenDto.setId(2);
        verify(citizenMapper, times(1)).toModel(citizenDto);

        assertEquals(citizenDtoCheck, citizenDto);

    }

    private Citizen getCitizenForTest(CitizenDto citizenDto, Passport passport) {
        Citizen citizen = new Citizen();
        citizen.setId(2);
        citizen.setFirstName(citizenDto.getFirstName());
        citizen.setLastName(citizenDto.getLastName());
        citizen.setPassport(passport);
        return citizen;
    }

    private CitizenDto getCitizenDtoForTest(PassportDto passportDto) {
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setFirstName("Mark");
        citizenDto.setLastName("Soups");
        citizenDto.setPassport(passportDto);
        return citizenDto;
    }

    private PassportDto getPassportDtoForTest(Passport passport) {
        PassportDto passportDto = new PassportDto();
        passportDto.setNumber(passport.getNumber());
        passportDto.setDateOfCreated(passport.getDateOfCreated());
        passportDto.setId(passport.getId());
        return passportDto;
    }

    private Passport getPassportForTest() {
        Passport passport = new Passport();
        passport.setNumber(UUID.randomUUID());
        passport.setDateOfCreated(new Date());
        passport.setId(1);
        return passport;
    }
}

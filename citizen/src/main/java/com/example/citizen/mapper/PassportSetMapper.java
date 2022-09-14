package com.example.citizen.mapper;

import com.example.citizen.dto.PassportDto;
import com.example.citizen.model.Passport;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = PassportMapper.class)
public interface PassportSetMapper {
    Set<PassportDto> toDto(Set<Passport> setPassport);
    Set<Passport> toModel(Set<PassportDto> setPassportDto);
}

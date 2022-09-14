package com.example.citizen.mapper;

import com.example.citizen.dto.PassportDto;
import com.example.citizen.model.Passport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    PassportDto toDto(Passport passport);
    Passport toModel(PassportDto passportDto);
}

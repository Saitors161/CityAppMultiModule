package com.example.citizen.mapper;

import com.example.citizen.model.Passport;
import com.example.common.dto.PassportDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    PassportDto toDto(Passport passport);
    Passport toModel(PassportDto passportDto);
}

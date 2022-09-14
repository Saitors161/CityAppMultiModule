package com.example.citizen.mapper;

import com.example.citizen.dto.CitizenDto;
import com.example.citizen.model.Citizen;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {CitizenMapper.class,PassportMapper.class})
public interface CitizenSetMapper {
    Set<Citizen> toModel(Set<CitizenDto> setCitizenDto);
    Set<CitizenDto> toDto(Set<Citizen> setCitizen);
}

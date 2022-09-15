package com.example.citizen.mapper;

import com.example.citizen.model.Citizen;
import com.example.common.dto.CitizenDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {CitizenMapper.class,PassportMapper.class})
public interface CitizenSetMapper {
    Set<Citizen> toModel(Set<CitizenDto> setCitizenDto);
    Set<CitizenDto> toDto(Set<Citizen> setCitizen);
}

package com.example.citizen.mapper;

import com.example.citizen.dto.CitizenDto;
import com.example.citizen.model.Citizen;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BuildSetMapper.class, CarSetMapper.class, PassportMapper.class})
public interface CitizenMapper {
    Citizen toModel(CitizenDto citizenDto);
    CitizenDto toDto(Citizen citizen);
}

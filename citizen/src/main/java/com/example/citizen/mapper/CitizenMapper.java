package com.example.citizen.mapper;

import com.example.citizen.model.Citizen;
import com.example.common.dto.CitizenDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BuildSetMapper.class, CarSetMapper.class, PassportMapper.class})
public interface CitizenMapper {
    Citizen toModel(CitizenDto citizenDto);
    CitizenDto toDto(Citizen citizen);
}

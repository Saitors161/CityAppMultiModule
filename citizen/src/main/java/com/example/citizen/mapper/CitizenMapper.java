package com.example.citizen.mapper;

import com.example.citizen.model.Citizen;
import com.example.common.dto.AddressDto;
import com.example.common.dto.BuildDto;
import com.example.common.dto.CitizenDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {PassportMapper.class}
        , unmappedSourcePolicy = ReportingPolicy.IGNORE
        , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CitizenMapper {

    Citizen toModel(CitizenDto citizenDto);
    CitizenDto toDto(Citizen citizen);
}

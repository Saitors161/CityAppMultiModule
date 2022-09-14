package com.example.citizen.mapper;

import com.example.citizen.dto.BuildDto;
import com.example.citizen.model.Build;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = BuildMapper.class)
public interface BuildSetMapper {
    Set<Build> toModel(Set<BuildDto> setBuildDto);
    Set<BuildDto> toDto(Set<Build> setBuild);
}

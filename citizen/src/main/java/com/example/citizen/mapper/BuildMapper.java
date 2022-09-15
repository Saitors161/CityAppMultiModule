package com.example.citizen.mapper;

import com.example.citizen.model.Build;
import com.example.common.dto.BuildDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuildMapper {
    Build toModel(BuildDto buildDto);
    BuildDto toDto(Build build);
}

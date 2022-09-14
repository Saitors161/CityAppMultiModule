package com.example.citizen.mapper;

import com.example.citizen.dto.BuildDto;
import com.example.citizen.dto.CarDto;
import com.example.citizen.model.Build;
import com.example.citizen.model.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuildMapper {
    Build toModel(BuildDto buildDto);
    BuildDto toDto(Build build);
}

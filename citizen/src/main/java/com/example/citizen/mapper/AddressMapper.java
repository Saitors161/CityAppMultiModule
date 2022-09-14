package com.example.citizen.mapper;

import com.example.citizen.dto.AddressDto;
import com.example.citizen.dto.BuildDto;
import com.example.citizen.model.Address;
import com.example.citizen.model.Build;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toModel(AddressDto addressDto);
    AddressDto toDto(Address address);
}

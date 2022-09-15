package com.example.citizen.mapper;

import com.example.citizen.model.Address;
import com.example.common.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toModel(AddressDto addressDto);
    AddressDto toDto(Address address);
}

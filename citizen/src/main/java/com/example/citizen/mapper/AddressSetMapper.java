package com.example.citizen.mapper;

import com.example.citizen.dto.AddressDto;
import com.example.citizen.model.Address;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface AddressSetMapper {
    Set<Address> toModel(Set<AddressDto> setAddressDto);
    Set<AddressDto> toDto(Set<Address> setAddress);
}

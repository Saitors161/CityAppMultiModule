package com.example.citizen.rest;

import com.example.citizen.dto.CitizenDto;
import com.example.citizen.dto.PassportDto;
import com.example.citizen.service.CitizenService;
import com.example.citizen.service.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/passports")
@RequiredArgsConstructor
public class PassportController {
    private final PassportService passportService;

    @GetMapping
    Set<PassportDto> getPassports() {
        return passportService.getAll();
    }

    @GetMapping("/{id}")
    PassportDto getPassportById(@PathVariable Integer id) {
        return null;
    }

    @PutMapping
    PassportDto updatePassport(PassportDto passportDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    void deletePassportById(Integer id) {
    }

}

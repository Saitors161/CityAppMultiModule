package com.example.citizen.controller;

import com.example.citizen.service.PassportService;
import com.example.common.dto.PassportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/passports")
@RequiredArgsConstructor
public class PassportController {
    private final PassportService passportService;

    //todo: open only for admin
    @GetMapping("/{id}")
    PassportDto getPassportById(@PathVariable Integer id) {
        return null;
    }


    @GetMapping
    Set<PassportDto> getPassports() {
        return passportService.getAll();
    }

    @PutMapping
    PassportDto updatePassport(PassportDto passportDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    void deletePassportById(Integer id) {
    }

}

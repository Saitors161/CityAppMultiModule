package com.example.citizen.controller;

import com.example.citizen.dto.CitizenDto;
import com.example.citizen.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/citizens")
@RequiredArgsConstructor
public class CitizenController {
    private final CitizenService citizenService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CitizenDto createCitizen(@RequestBody CitizenDto citizenDto) {
        return citizenService.save(citizenDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<CitizenDto> getCitizens() {
        return citizenService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CitizenDto getCitizenById(@PathVariable Integer id) {
        return citizenService.getById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CitizenDto updateCitizen(@RequestBody CitizenDto citizenDto) {
        return citizenService.update(citizenDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCitizenById(@PathVariable Integer id) {
        citizenService.deleteById(id);
    }

}

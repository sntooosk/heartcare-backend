package com.etec.backend.controllers;

import com.etec.backend.dtos.MedicationResponseDTO;
import com.etec.backend.entities.Medication;
import com.etec.backend.services.MedicationService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/medication")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @GetMapping("/user/{userId}")
    public List<MedicationResponseDTO> findByUserId(@PathVariable Long userId) {
        return medicationService.findByUserId(userId);
    }

    @PostMapping("/")
    public Object create(@RequestBody Medication medication) {
        return medicationService.create(medication);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return medicationService.delete(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Long id, @RequestBody Medication medication) {
        return medicationService.update(id, medication);
    }
}

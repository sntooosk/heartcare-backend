package com.etec.backend.controller;

import com.etec.backend.dto.PressureResponseDTO;
import com.etec.backend.entity.Pressure;
import com.etec.backend.entity.User;
import com.etec.backend.service.PressureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pressure")
@RequiredArgsConstructor
public class PressureController {

    private final PressureService pressureService;

    @GetMapping
    public List<PressureResponseDTO> list() {
        return pressureService.list();
    }

    @GetMapping("/{id}")
    public List<PressureResponseDTO> findByUserId(@PathVariable Long userId) {
        return pressureService.findbyUserId(userId);
    }
    @PostMapping("/")
    public PressureResponseDTO create(@RequestBody Pressure pressure) {
        return pressureService.create(pressure);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return pressureService.delete(id);
    }

    @PutMapping("/{id}")
    public PressureResponseDTO update(@PathVariable Long id, @RequestBody Pressure pressure) {
        return pressureService.update(id, pressure);
    }
}

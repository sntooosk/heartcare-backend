package com.etec.backend.controller;

import com.etec.backend.dto.PressureResponseDTO;
import com.etec.backend.entity.Pressure;
import com.etec.backend.service.PressureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pressure")
@RequiredArgsConstructor
public class PressureController {

    private final PressureService pressureService;

    @GetMapping("/")
    public List<PressureResponseDTO> getAll() {
        return pressureService.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<PressureResponseDTO> findByUserId(@PathVariable Long userId) {
        return pressureService.findByUserId(userId);
    }

    @PostMapping("/")
    public Object create(@RequestBody Pressure pressure) {
        return pressureService.create(pressure);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return pressureService.delete(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Long id, @RequestBody Pressure pressure) {
        return pressureService.update(id, pressure);
    }
}

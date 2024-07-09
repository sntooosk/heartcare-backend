package com.etec.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.etec.dto.PressureResponseDTO;
import com.etec.entities.Pressure;
import com.etec.service.PressureService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pressure")
@RequiredArgsConstructor
public class PressureController {

    private final PressureService pressureService;


    @GetMapping("/")
    public List<PressureResponseDTO> findByUserId(@Param("userId") Integer userId) {
        return pressureService.findByUserId(userId);
    }

    @PostMapping("/")
    public Object create(@RequestBody Pressure pressure) {
        return pressureService.create(pressure);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        return pressureService.delete(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody Pressure pressure) {
        return pressureService.update(id, pressure);
    }
}

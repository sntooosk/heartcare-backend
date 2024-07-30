package com.etec.backend.controller;

import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Pressure;
import com.etec.backend.service.PressureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/pressure")
@RequiredArgsConstructor
public class PressureController {

    private final PressureService pressureService;

    @GetMapping("/")
    public Object findAllConcatenated() {
        return pressureService.getAllConcat();
    }

    @GetMapping("/user/{userId}")
    public Object findByUserId(@PathVariable Long userId) {
        return pressureService.findByUserId(userId);
    }

    @PostMapping
    public ResponseDTO create(@RequestBody Pressure pressure) {
        return pressureService.create(pressure);
    }

    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Long id) {
       return pressureService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseDTO update(@PathVariable Long id, @RequestBody Pressure pressure) {
        return pressureService.update(id, pressure);
    }
}

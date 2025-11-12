package com.lockers.controller;

import com.lockers.dto.EstudianteDTO; // Import DTO
import com.lockers.service.EstudianteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @PostMapping
    public EstudianteDTO registrar(@RequestBody EstudianteDTO estudiante) { // Usa DTO
        return service.registrar(estudiante);
    }

    @GetMapping("/{documento}")
    public EstudianteDTO buscar(@PathVariable String documento) { // Retorna DTO
        return service.buscarPorDocumento(documento);
    }
}
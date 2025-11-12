package com.lockers.controller;

import com.lockers.dto.AlquilerDTO;
import com.lockers.dto.AlquilerRequestDTO;
import com.lockers.service.AlquilerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {
    private final AlquilerService service;

    public AlquilerController(AlquilerService service) {
        this.service = service;
    }

    // Endpoint mejorado: más RESTful y usa DTO
    @PostMapping
    public AlquilerDTO alquilar(@RequestBody AlquilerRequestDTO requestDTO) {
        return service.alquilarLocker(requestDTO);
    }

    @PutMapping("/liberar/{lockerId}")
    public boolean liberar(@PathVariable int lockerId) {
        return service.liberarLocker(lockerId);
    }

    @GetMapping
    public List<AlquilerDTO> listar() { // Retorna lista de DTOs
        return service.obtenerAlquileres();
    }
}
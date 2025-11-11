package com.lockers.controller;
//AlquilerController
import com.lockers.model.Alquiler;
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

    @PostMapping("/alquilar")
    public Alquiler alquilar(@RequestParam String documento, @RequestParam int lockerId) {
        return service.alquilarLocker(documento, lockerId);
    }

    @PutMapping("/liberar/{lockerId}")
    public boolean liberar(@PathVariable int lockerId) {
        return service.liberarLocker(lockerId);
    }

    @GetMapping
    public List<Alquiler> listar() {
        return service.obtenerAlquileres();
    }
}
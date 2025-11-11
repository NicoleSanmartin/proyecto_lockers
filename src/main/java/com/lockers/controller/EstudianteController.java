package com.lockers.controller;
//EstudianteController
import com.lockers.model.Estudiante;
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
    public Estudiante registrar(@RequestBody Estudiante estudiante) {
        return service.registrar(estudiante);
    }

    @GetMapping("/{documento}")
    public Estudiante buscar(@PathVariable String documento) {
        return service.buscarPorDocumento(documento);
    }
}
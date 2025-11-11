package com.lockers.service;
//EstudianteService
import com.lockers.model.Estudiante;
import com.lockers.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService {
    private final EstudianteRepository repository;

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    public Estudiante registrar(Estudiante e) {
        return repository.save(e);
    }

    public Estudiante buscarPorDocumento(String documento) {
        return repository.findById(documento)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }
}

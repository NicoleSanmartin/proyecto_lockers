package com.lockers.service;

import com.lockers.dto.EstudianteDTO;
import com.lockers.model.Estudiante;
import com.lockers.repository.EstudianteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService {
    private final EstudianteRepository repository;
    private final ModelMapper modelMapper; // Inyectado

    public EstudianteService(EstudianteRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper; // Inyectado
    }

    public EstudianteDTO registrar(EstudianteDTO estudianteDTO) {
        // Convierte DTO a Entidad
        Estudiante estudiante = modelMapper.map(estudianteDTO, Estudiante.class);
        Estudiante estudianteGuardado = repository.save(estudiante);
        // Convierte Entidad a DTO para retornar
        return modelMapper.map(estudianteGuardado, EstudianteDTO.class);
    }

    public EstudianteDTO buscarPorDocumento(String documento) {
        Estudiante estudiante = repository.findById(documento)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        // Convierte Entidad a DTO
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }

    protected Estudiante buscarEntidadPorDocumento(String documento) {
        return repository.findById(documento)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }
}

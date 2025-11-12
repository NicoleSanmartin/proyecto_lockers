package com.lockers.service;

import com.lockers.dto.AlquilerDTO;
import com.lockers.dto.AlquilerRequestDTO;
import com.lockers.model.Alquiler;
import com.lockers.model.Estudiante;
import com.lockers.model.Locker;
import com.lockers.repository.AlquilerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlquilerService {
    private final AlquilerRepository repository;
    private final EstudianteService estudianteService;
    private final LockerService lockerService;
    private final ModelMapper modelMapper;

    public AlquilerService(AlquilerRepository repository, EstudianteService estudianteService,
                           LockerService lockerService, ModelMapper modelMapper) {
        this.repository = repository;
        this.estudianteService = estudianteService;
        this.lockerService = lockerService;
        this.modelMapper = modelMapper;
    }

    public AlquilerDTO alquilarLocker(AlquilerRequestDTO requestDTO) {
        // Usamos los métodos internos del servicio para obtener las ENTIDADES
        Estudiante estudiante = estudianteService.buscarEntidadPorDocumento(requestDTO.getDocumentoEstudiante());
        Locker locker = lockerService.buscarEntidadLocker(requestDTO.getLockerId()); // (Necesitarías crear este método en LockerService)

        if (!locker.isDisponible())
            throw new RuntimeException("Locker no disponible.");

        lockerService.actualizarEstado(locker, false);
        Alquiler nuevoAlquiler = new Alquiler(estudiante, locker);
        Alquiler alquilerGuardado = repository.save(nuevoAlquiler);

        // Convertimos la Entidad a DTO para retornarla
        return modelMapper.map(alquilerGuardado, AlquilerDTO.class);
    }

    public boolean liberarLocker(int lockerId) {
        Alquiler alquiler = repository.findByLockerIdAndEstado(lockerId, "ACTIVO");
        if (alquiler == null)
            throw new RuntimeException("Locker no tiene alquiler activo.");

        alquiler.setEstado("INACTIVO");
        lockerService.actualizarEstado(alquiler.getLocker(), true);
        repository.save(alquiler);
        return true;
    }

    public List<AlquilerDTO> obtenerAlquileres() {
        return repository.findAll()
                .stream()
                .map(alquiler -> modelMapper.map(alquiler, AlquilerDTO.class)) // Mapea cada item
                .collect(Collectors.toList());
    }
}
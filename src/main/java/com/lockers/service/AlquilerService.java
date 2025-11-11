package com.lockers.service;
//AlquilerService
import com.lockers.model.Alquiler;
import com.lockers.model.Estudiante;
import com.lockers.model.Locker;
import com.lockers.repository.AlquilerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlquilerService {
    private final AlquilerRepository repository;
    private final EstudianteService estudianteService;
    private final LockerService lockerService;

    public AlquilerService(AlquilerRepository repository, EstudianteService estudianteService, LockerService lockerService) {
        this.repository = repository;
        this.estudianteService = estudianteService;
        this.lockerService = lockerService;
    }

    public Alquiler alquilarLocker(String documento, int lockerId) {
        Estudiante estudiante = estudianteService.buscarPorDocumento(documento);
        Locker locker = lockerService.buscarLocker(lockerId);

        if (!locker.isDisponible())
            throw new RuntimeException("Locker no disponible.");

        lockerService.actualizarEstado(locker, false);
        return repository.save(new Alquiler(estudiante, locker));
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

    public List<Alquiler> obtenerAlquileres() {
        return repository.findAll();
    }
}
package com.lockers.service;

import com.lockers.dto.AlquilerRequest;
import com.lockers.model.Alquiler;
import com.lockers.model.Locker;
import com.lockers.model.Usuario;
import com.lockers.repository.AlquilerRepository;
import com.lockers.repository.LockerRepository;
import com.lockers.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LockerService {

    private final LockerRepository lockerRepository;
    private final AlquilerRepository alquilerRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LockerService(LockerRepository lockerRepository,
                         AlquilerRepository alquilerRepository,
                         UsuarioRepository usuarioRepository) {
        this.lockerRepository = lockerRepository;
        this.alquilerRepository = alquilerRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // ----------------------------------------------------------------------
    // Métodos de gestión de Lockers
    // ----------------------------------------------------------------------

    public List<Locker> getAllLockers() {
        return lockerRepository.findAll();
    }

    public Optional<Locker> findLocker(Long id) {
        return lockerRepository.findById(id);
    }

    public Locker addLocker(Locker locker) {
        locker.setDisponible(true);
        return lockerRepository.save(locker);
    }

    public Optional<Locker> updateLocker(Long id, Locker lockerActualizado) {
        return lockerRepository.findById(id).map(lockerExistente -> {
            lockerExistente.setUbicacion(lockerActualizado.getUbicacion());
            lockerExistente.setDisponible(lockerActualizado.isDisponible());
            return lockerRepository.save(lockerExistente);
        });
    }

    public boolean deleteLocker(Long id) {
        if (lockerRepository.existsById(id)) {
            lockerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ----------------------------------------------------------------------
    // Métodos de gestión de Alquileres
    // ----------------------------------------------------------------------

    public List<Alquiler> getAllAlquileres() {
        return alquilerRepository.findAll();
    }

    public ResponseEntity<String> alquilar(AlquilerRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(request.getIdUsuario());
        Optional<Locker> lockerOpt = lockerRepository.findById(request.getIdLocker());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
        if (lockerOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Locker no encontrado.");
        }

        Locker locker = lockerOpt.get();
        if (!locker.isDisponible()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El locker ya está ocupado.");
        }

        // Crear nuevo alquiler
        Alquiler nuevoAlquiler = new Alquiler();
        nuevoAlquiler.setLocker(locker);
        nuevoAlquiler.setUsuario(usuarioOpt.get());
        nuevoAlquiler.setFechaInicio(LocalDateTime.now());
        nuevoAlquiler.setActivo(true);
        alquilerRepository.save(nuevoAlquiler);

        // Actualizar estado del locker
        locker.setDisponible(false);
        lockerRepository.save(locker);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Locker " + locker.getId() + " alquilado correctamente a "
                        + usuarioOpt.get().getNombre() + ".");
    }

    public ResponseEntity<String> liberar(Long idLocker) {
        Optional<Locker> lockerOpt = lockerRepository.findById(idLocker);

        if (lockerOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Locker no encontrado.");
        }

        Locker locker = lockerOpt.get();
        if (locker.isDisponible()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El locker ya está disponible.");
        }

        // Buscar alquiler activo de ese locker
        Optional<Alquiler> alquilerOpt = alquilerRepository.findByLockerAndActivoTrue(locker);

        if (alquilerOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un alquiler activo para este locker.");
        }

        Alquiler alquiler = alquilerOpt.get();
        alquiler.setActivo(false);
        alquiler.setFechaFin(LocalDateTime.now());
        alquilerRepository.save(alquiler);

        locker.setDisponible(true);
        lockerRepository.save(locker);

        return ResponseEntity.ok("Locker " + locker.getId() + " liberado correctamente.");
    }
}

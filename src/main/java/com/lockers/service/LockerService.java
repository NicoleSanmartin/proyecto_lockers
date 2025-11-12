package com.lockers.service;
//LockerService
import com.lockers.model.Locker;
import com.lockers.repository.LockerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LockerService {
    private final LockerRepository repository;

    public LockerService(LockerRepository repository) {
        this.repository = repository;
    }

    public List<Locker> obtenerLockers() {
        return repository.findAll();
    }

    public Locker buscarLocker(int id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Locker no encontrado"));
    }

    public Locker registrarLocker(Locker locker) {
        return repository.save(locker);
    }

    public Locker actualizarEstado(Locker locker, boolean disponible) {
        locker.setDisponible(disponible);
        return repository.save(locker);
    }

    protected Locker buscarEntidadLocker(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locker no encontrado"));
    }

}


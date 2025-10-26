package com.lockers.service;

import com.lockers.model.Alquiler;
import com.lockers.model.Locker;
import com.lockers.repository.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LockerService {

    private final LockerRepository repository;

    @Autowired
    public LockerService(LockerRepository repository) {
        this.repository = repository;
    }

    public List<Locker> getAllLockers() {
        return repository.getLockers();
    }

    public List<Alquiler> getAllAlquileres() {
        return repository.getAlquileres();
    }

    public Optional<Locker> findLocker(int id) {
        return repository.findById(id);
    }

    public boolean alquilar(int lockerId, String nombre, String documento) {
        return repository.alquilarLocker(lockerId, nombre, documento);
    }

    public boolean liberar(int lockerId) {
        return repository.liberarLocker(lockerId);
    }
}

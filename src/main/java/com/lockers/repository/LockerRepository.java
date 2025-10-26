package com.lockers.repository;

import com.lockers.model.Alquiler;
import com.lockers.model.Locker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LockerRepository {

    private final List<Locker> lockers = new ArrayList<>();
    private final List<Alquiler> alquileres = new ArrayList<>();

    public LockerRepository() {
        // Inicializa 10 lockers al arrancar
        for (int i = 1; i <= 10; i++) {
            lockers.add(new Locker(i));
        }
    }

    public List<Locker> getLockers() {
        return lockers;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }

    public Optional<Locker> findById(int id) {
        return lockers.stream().filter(l -> l.getId() == id).findFirst();
    }

    public boolean alquilarLocker(int id, String nombre, String documento) {
        Optional<Locker> lockerOpt = findById(id);
        if (lockerOpt.isPresent()) {
            Locker locker = lockerOpt.get();
            if (locker.isDisponible()) {
                locker.setDisponible(false);
                alquileres.add(new Alquiler(nombre, documento, id));
                return true;
            }
        }
        return false;
    }

    public boolean liberarLocker(int id) {
        Optional<Locker> lockerOpt = findById(id);
        if (lockerOpt.isPresent()) {
            Locker locker = lockerOpt.get();
            if (!locker.isDisponible()) {
                locker.setDisponible(true);
                return true;
            }
        }
        return false;
    }
}

package com.lockers.service;

import com.lockers.model.Alquiler;
import com.lockers.model.Estudiante;
import com.lockers.model.Locker;
import com.lockers.repository.LockerRepository;

import java.util.List;
import java.util.Optional;

public class LockerService {
    private LockerRepository repository;

    public LockerService(LockerRepository repository) {
        this.repository = repository;
    }

    // === Registrar entidades ===
    public void registrarEstudiante(String nombre, String documento) {
        repository.addEstudiante(new Estudiante(nombre, documento));
        System.out.println("Estudiante registrado (si no existía).");
    }

    public void registrarLocker(int id) {
        repository.addLocker(new Locker(id));
    }

    // === Alquiler de lockers ===
    public boolean alquilarLocker(String documento, int lockerId) {
        Estudiante estudiante = repository.findEstudianteByDocumento(documento);
        if (estudiante == null) {
            System.out.println("El estudiante no está registrado.");
            return false;
        }

        List<Locker> lockers = repository.getLockers();
        Optional<Locker> lockerOpt = lockers.stream()
                .filter(l -> l.getId() == lockerId && l.isDisponible())
                .findFirst();

        if (lockerOpt.isPresent()) {
            Locker locker = lockerOpt.get();
            repository.addAlquiler(new Alquiler(estudiante, locker));
            System.out.println("Locker alquilado correctamente.");
            return true;
        } else {
            System.out.println("El locker no está disponible.");
            return false;
        }
    }

    // === Liberar lockers ===
    public boolean liberarLocker(int lockerId) {
        boolean result = repository.liberarLocker(lockerId);
        if (result) {
            System.out.println("Locker liberado.");
        } else {
            System.out.println("No se pudo liberar el locker.");
        }
        return result;
    }

    // === Mostrar datos ===
    public void mostrarLockers() {
        for (Locker l : repository.getLockers()) {
            System.out.println("Locker " + l.getId() + ": " + (l.isDisponible() ? "Disponible" : "Ocupado"));
        }
    }

    public void mostrarAlquileres() {
        for (Alquiler a : repository.getAlquileres()) {
            System.out.println("Locker " + a.getLocker().getId() +
                    " alquilado por " + a.getEstudiante().getNombre() +
                    " desde " + a.getFechaInicio());
        }
    }
}

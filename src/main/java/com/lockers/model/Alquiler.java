package com.lockers.model;

import java.time.LocalDateTime;

public class Alquiler {
    private Estudiante estudiante;
    private Locker locker;
    private LocalDateTime fechaInicio;

    public Alquiler(Estudiante estudiante, Locker locker) {
        this.estudiante = estudiante;
        this.locker = locker;
        this.fechaInicio = LocalDateTime.now();
    }

    // Constructor adicional para reconstruir desde la BD
    public Alquiler(Estudiante estudiante, Locker locker, LocalDateTime fechaInicio) {
        this.estudiante = estudiante;
        this.locker = locker;
        this.fechaInicio = fechaInicio;
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Locker getLocker() { return locker; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
}

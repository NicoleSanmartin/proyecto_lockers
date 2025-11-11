package com.lockers.model;
//Alquiler
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Locker locker;

    private LocalDateTime fechaInicio;
    private String estado;

    public Alquiler() { }

    public Alquiler(Estudiante estudiante, Locker locker) {
        this.estudiante = estudiante;
        this.locker = locker;
        this.fechaInicio = LocalDateTime.now();
        this.estado = "ACTIVO";
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Locker getLocker() { return locker; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
}

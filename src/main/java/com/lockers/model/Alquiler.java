package com.lockers.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alquileres")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "locker_id")
    @JsonBackReference // ✅ evita recursión infinita desde aquí
    private Locker locker;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private boolean activo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public Alquiler() {}

    public Alquiler(Locker locker, Usuario usuario, boolean activo, LocalDateTime fechaInicio) {
        this.locker = locker;
        this.usuario = usuario;
        this.activo = activo;
        this.fechaInicio = fechaInicio;
    }

    public Long getId() { return id; }
    public Locker getLocker() { return locker; }
    public void setLocker(Locker locker) { this.locker = locker; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }

    @Override
    public String toString() {
        return "Locker " + locker.getId() + " alquilado por " + usuario.getNombre() +
                " (" + usuario.getDocumento() + ")";
    }
}

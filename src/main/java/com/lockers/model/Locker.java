package com.lockers.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "lockers")
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ubicacion;
    private boolean disponible;

    @OneToMany(mappedBy = "locker", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // ✅ evita recursión infinita al serializar
    private List<Alquiler> alquileres;

    public Locker() {}

    public Locker(String ubicacion, boolean disponible) {
        this.ubicacion = ubicacion;
        this.disponible = disponible;
    }

    public Long getId() { return id; }
    public String getUbicacion() { return ubicacion; }
    public boolean isDisponible() { return disponible; }

    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public List<Alquiler> getAlquileres() { return alquileres; }
    public void setAlquileres(List<Alquiler> alquileres) { this.alquileres = alquileres; }
}

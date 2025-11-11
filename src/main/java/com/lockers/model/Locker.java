package com.lockers.model;
//Locker
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Locker {
    @Id
    private int id;
    private boolean disponible = true;

    public Locker() { }

    public Locker(int id) {
        this.id = id;
        this.disponible = true;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return "Locker " + id + " - " + (disponible ? "Libre" : "Ocupado");
    }
}
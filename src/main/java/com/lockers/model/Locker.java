package com.lockers.model;

public class Locker {
    private int id;
    private boolean disponible;

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

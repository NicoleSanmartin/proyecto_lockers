package com.lockers.model;

public class Alquiler {
    private String nombre;
    private String documento;
    private int lockerId;

    public Alquiler(String nombre, String documento, int lockerId) {
        this.nombre = nombre;
        this.documento = documento;
        this.lockerId = lockerId;
    }

    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public int getLockerId() { return lockerId; }

    @Override
    public String toString() {
        return "Locker " + lockerId + " alquilado por " + nombre + " (" + documento + ")";
    }
}

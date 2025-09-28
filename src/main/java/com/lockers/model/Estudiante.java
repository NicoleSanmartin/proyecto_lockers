package com.lockers.model;

public class Estudiante {
    private String nombre;
    private String documento;

    public Estudiante(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento;
    }

    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
}

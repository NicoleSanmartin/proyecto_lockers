package com.lockers.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String documento;

    public Usuario() {}

    public Usuario(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDocumento(String documento) { this.documento = documento; }
}

package com.lockers.repository;

import com.lockers.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí podrías agregar consultas personalizadas si luego las necesitas
}

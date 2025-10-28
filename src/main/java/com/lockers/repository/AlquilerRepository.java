package com.lockers.repository;

import com.lockers.model.Alquiler;
import com.lockers.model.Locker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    // Busca el alquiler activo de un locker específico
    Optional<Alquiler> findByLockerAndActivoTrue(Locker locker);
}

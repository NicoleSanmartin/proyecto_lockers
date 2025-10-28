package com.lockers.repository;

import com.lockers.model.Locker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockerRepository extends JpaRepository<Locker, Long> {
    // Puedes agregar consultas personalizadas si las necesitas, por ejemplo:
    // List<Locker> findByDisponibleTrue();
}

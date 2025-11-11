package com.lockers.repository;
//AlquilerRepository
import com.lockers.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    Alquiler findByLockerIdAndEstado(int lockerId, String estado);
}
package com.lockers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AlquilerDTO {
    private Long id;
    private EstudianteDTO estudiante;
    private LockerDTO locker;
    private LocalDateTime fechaInicio;
    private String estado;
}
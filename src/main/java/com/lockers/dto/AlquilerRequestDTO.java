package com.lockers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlquilerRequestDTO {
    private String documentoEstudiante;
    private int lockerId;
}
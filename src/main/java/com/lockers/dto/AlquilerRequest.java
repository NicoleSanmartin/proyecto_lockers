package com.lockers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO (Data Transfer Object) usado para registrar un nuevo alquiler.
 * Contiene solo la información necesaria que el cliente debe enviar al servidor.
 */
@Schema(name = "AlquilerRequest", description = "Datos necesarios para crear un alquiler")
public class AlquilerRequest {

    @Schema(description = "ID del usuario que solicita el alquiler", example = "1", required = true)
    @NotNull(message = "El idUsuario es obligatorio")
    @Positive(message = "El idUsuario debe ser un número positivo")
    private Long idUsuario;

    @Schema(description = "ID del locker que se desea alquilar", example = "10", required = true)
    @NotNull(message = "El idLocker es obligatorio")
    @Positive(message = "El idLocker debe ser un número positivo")
    private Long idLocker;

    public AlquilerRequest() {
    }

    public AlquilerRequest(Long idUsuario, Long idLocker) {
        this.idUsuario = idUsuario;
        this.idLocker = idLocker;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdLocker() {
        return idLocker;
    }

    public void setIdLocker(Long idLocker) {
        this.idLocker = idLocker;
    }

    @Override
    public String toString() {
        return "AlquilerRequest{" +
                "idUsuario=" + idUsuario +
                ", idLocker=" + idLocker +
                '}';
    }
}

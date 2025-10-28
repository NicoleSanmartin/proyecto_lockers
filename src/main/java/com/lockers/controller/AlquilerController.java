package com.lockers.controller;

import com.lockers.dto.AlquilerRequest;
import com.lockers.model.Alquiler;
import com.lockers.service.LockerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquileres")
@Tag(name = "Gestión de Alquileres", description = "Endpoints para alquilar, liberar y listar lockers alquilados.")
public class AlquilerController {

    private final LockerService lockerService;

    @Autowired
    public AlquilerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    @Operation(
            summary = "Listar todos los alquileres",
            description = "Obtiene la lista completa de lockers actualmente alquilados."
    )
    @GetMapping
    public ResponseEntity<List<Alquiler>> getAllAlquileres() {
        List<Alquiler> lista = lockerService.getAllAlquileres();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @Operation(
            summary = "Registrar un nuevo alquiler",
            description = "Permite alquilar un locker disponible a un usuario existente. " +
                    "Requiere enviar los IDs de usuario y locker."
    )
    @PostMapping("/alquilar")
    public ResponseEntity<String> alquilar(@Valid @RequestBody AlquilerRequest request) {
        ResponseEntity<String> resultado = lockerService.alquilar(request);
        return resultado != null
                ? resultado
                : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No se pudo realizar el alquiler.");
    }

    @Operation(
            summary = "Liberar un locker",
            description = "Libera un locker alquilado y lo marca como disponible nuevamente."
    )
    @PutMapping("/liberar/{idLocker}")
    public ResponseEntity<String> liberar(@PathVariable Long idLocker) {
        ResponseEntity<String> resultado = lockerService.liberar(idLocker);
        return resultado != null
                ? resultado
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Locker no encontrado o ya está libre.");
    }
}

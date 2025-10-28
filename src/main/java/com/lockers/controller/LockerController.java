package com.lockers.controller;

import com.lockers.model.Locker;
import com.lockers.service.LockerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lockers")
@Tag(name = "Gestión de Lockers", description = "Operaciones para consultar, agregar y cambiar el estado de los lockers.")
public class LockerController {

    private final LockerService lockerService;

    @Autowired
    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    @Operation(summary = "Listar todos los lockers", description = "Devuelve la lista completa de lockers con su estado actual (disponible u ocupado).")
    @GetMapping
    public ResponseEntity<List<Locker>> getAllLockers() {
        List<Locker> lockers = lockerService.getAllLockers();
        if (lockers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lockers);
    }

    @Operation(summary = "Consultar un locker por ID", description = "Permite obtener la información de un locker específico por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Locker> getLockerById(@PathVariable Long id) {
        Optional<Locker> lockerOpt = lockerService.findLocker(id);
        return lockerOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Agregar un nuevo locker", description = "Crea un nuevo locker disponible en el sistema.")
    @PostMapping
    public ResponseEntity<Locker> addLocker(@RequestBody Locker locker) {
        Locker nuevoLocker = lockerService.addLocker(locker);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLocker);
    }

    @Operation(summary = "Actualizar un locker", description = "Permite modificar los datos o el estado de un locker existente.")
    @PutMapping("/{id}")
    public ResponseEntity<Locker> updateLocker(@PathVariable Long id, @RequestBody Locker lockerActualizado) {
        Optional<Locker> actualizado = lockerService.updateLocker(id, lockerActualizado);
        return actualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un locker", description = "Elimina un locker del sistema por su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocker(@PathVariable Long id) {
        boolean eliminado = lockerService.deleteLocker(id);
        return eliminado ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

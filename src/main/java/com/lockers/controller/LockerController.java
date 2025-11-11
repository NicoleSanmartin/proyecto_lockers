package com.lockers.controller;
//LockerController
import com.lockers.model.Locker;
import com.lockers.service.LockerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {
    private final LockerService service;

    public LockerController(LockerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Locker> listarLockers() {
        return service.obtenerLockers();
    }

    @GetMapping("/{id}")
    public Locker obtenerLocker(@PathVariable int id) {
        return service.buscarLocker(id);
    }

    @PostMapping
    public Locker registrarLocker(@RequestBody Locker locker) {
        return service.registrarLocker(locker);
    }
}
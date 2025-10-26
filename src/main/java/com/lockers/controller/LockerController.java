package com.lockers.controller;

import com.lockers.model.Locker;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    @GetMapping
    public List<Locker> getAllLockers() {
        return List.of(new Locker(1), new Locker(2));
    }

    @GetMapping("/{id}")
    public Locker getLocker(@PathVariable int id) {
        return new Locker(id);
    }
}

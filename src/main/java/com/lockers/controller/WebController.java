package com.lockers.controller;

import com.lockers.model.Locker;
import com.lockers.service.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    private final LockerService lockerService;

    @Autowired
    public WebController(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Locker> lockers = lockerService.getAllLockers();
        model.addAttribute("lockers", lockers);
        return "index";
    }

    @PostMapping("/alquilar")
    public String alquilar(@RequestParam("lockerId") int lockerId,
                           @RequestParam("nombre") String nombre,
                           @RequestParam("documento") String documento,
                           Model model) {
        boolean ok = lockerService.alquilar(lockerId, nombre, documento);
        if (ok) {
            model.addAttribute("success", "Locker " + lockerId + " alquilado correctamente para " + nombre + ".");
        } else {
            model.addAttribute("error", "No se pudo alquilar el locker " + lockerId + ". Revisa que exista y esté disponible.");
        }
        model.addAttribute("lockers", lockerService.getAllLockers());
        return "index";
    }

    @PostMapping("/liberar")
    public String liberar(@RequestParam("lockerId") int lockerId, Model model) {
        boolean ok = lockerService.liberar(lockerId);
        if (ok) {
            model.addAttribute("success", "Locker " + lockerId + " liberado correctamente.");
        } else {
            model.addAttribute("error", "No se pudo liberar el locker " + lockerId + ". Revisa que exista y esté ocupado.");
        }
        model.addAttribute("lockers", lockerService.getAllLockers());
        return "index";
    }
}

package com.example.demo.controllers;

import com.example.demo.entities.Sede;
import com.example.demo.repositories.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sedes")
@CrossOrigin(origins = "*")
public class SedeController {

    @Autowired
    private SedeRepository sedeRepository;

    @GetMapping
    public List<Sede> obtenerTodas() {
        return sedeRepository.findAll();
    }
}
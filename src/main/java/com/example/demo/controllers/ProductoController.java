package com.example.demo.controllers;

import com.example.demo.entities.Producto;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @GetMapping("/novedades")
    public List<Producto> obtenerNovedades() {
        return productoRepository.findByEsTemporadaTrue();
    }

    @GetMapping("/frecuentes")
    public List<Producto> obtenerFrecuentes() {
        return productoRepository.findByEsFrecuenteTrue();
    }
}
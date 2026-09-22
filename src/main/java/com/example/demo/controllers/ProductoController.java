package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entities.Producto;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Producto>>> obtenerTodos() {
        return ResponseEntity.ok(ApiResponse.success("Productos obtenidos correctamente", productoRepository.findAll()));
    }

    @GetMapping("/novedades")
    public ResponseEntity<ApiResponse<List<Producto>>> obtenerNovedades() {
        return ResponseEntity.ok(ApiResponse.success("Novedades obtenidas correctamente", productoRepository.findByEsTemporadaTrue()));
    }

    @GetMapping("/frecuentes")
    public ResponseEntity<ApiResponse<List<Producto>>> obtenerFrecuentes() {
        return ResponseEntity.ok(ApiResponse.success("Productos frecuentes obtenidos correctamente", productoRepository.findByEsFrecuenteTrue()));
    }
}
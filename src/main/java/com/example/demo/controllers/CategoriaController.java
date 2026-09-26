package com.example.demo.controllers;

import com.example.demo.dto.CategoriaDTO; // Importa tu DTO
import com.example.demo.services.CategoriaService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*") 
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> obtenerCategorias() { // Cambiado a List<CategoriaDTO>
        return categoriaService.listarTodas(); 
    }
}
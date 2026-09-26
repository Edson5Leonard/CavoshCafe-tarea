package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CategoriaDTO; 
import com.example.demo.services.CategoriaService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*") 
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

   @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaDTO>>> obtenerCategorias() {
        List<CategoriaDTO> categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(
            ApiResponse.success("Categorías obtenidas correctamente", categorias)
        );
    }
}
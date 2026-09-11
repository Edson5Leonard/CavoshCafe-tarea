package com.example.demo.repositories;


import com.example.demo.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByEsTemporadaTrue();
    List<Producto> findByEsFrecuenteTrue();
    List<Producto> findByCategoriaId(Long categoriaId);
}
package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(name = "precio_base", nullable = false)
    private BigDecimal precioBase;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "es_temporada")
    private Boolean esTemporada = false;

    @Column(name = "es_frecuente")
    private Boolean esFrecuente = false;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
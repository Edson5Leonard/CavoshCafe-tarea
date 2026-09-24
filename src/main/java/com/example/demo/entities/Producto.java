package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
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
    @JsonIgnoreProperties("productos") 
    private Categoria categoria;

    public Producto() {}

    public Producto(Long id, String nombre, String descripcion, BigDecimal precioBase, String imagenUrl, Boolean esTemporada, Boolean esFrecuente, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.imagenUrl = imagenUrl;
        this.esTemporada = esTemporada;
        this.esFrecuente = esFrecuente;
        this.categoria = categoria;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public Boolean getEsTemporada() { return esTemporada; }
    public void setEsTemporada(Boolean esTemporada) { this.esTemporada = esTemporada; }

    public Boolean getEsFrecuente() { return esFrecuente; }
    public void setEsFrecuente(Boolean esFrecuente) { this.esFrecuente = esFrecuente; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
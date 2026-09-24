package com.example.demo.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "opciones_personalizacion")
public class OpcionPersonalizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_opcion", nullable = false)
    private String tipoOpcion; 

    @Column(nullable = false)
    private String nombre;

    @Column(name = "precio_extra")
    private BigDecimal precioExtra = BigDecimal.ZERO;

    public OpcionPersonalizacion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipoOpcion() { return tipoOpcion; }
    public void setTipoOpcion(String tipoOpcion) { this.tipoOpcion = tipoOpcion; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public BigDecimal getPrecioExtra() { return precioExtra; }
    public void setPrecioExtra(BigDecimal precioExtra) { this.precioExtra = precioExtra; }
}
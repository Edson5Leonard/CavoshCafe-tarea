package com.example.demo.dto;

import com.example.demo.entities.Usuario;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private Integer puntos;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.puntos = usuario.getPuntos();
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public Integer getPuntos() { return puntos; }
}
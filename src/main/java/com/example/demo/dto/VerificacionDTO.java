package com.example.demo.dto;

public class VerificacionDTO {
    private String email;
    private String codigo;

    public VerificacionDTO() {}

    public VerificacionDTO(String email, String codigo) {
        this.email = email;
        this.codigo = codigo;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}
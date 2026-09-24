package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoRequestDTO {

    private Long usuarioId;
    private Long sedeId;
    private String metodoEntrega; 
    private String metodoPago;    
    private LocalDateTime fechaProgramada;
    private BigDecimal subtotal;
    private BigDecimal descuento;
    private BigDecimal montoTotal;
    private List<DetallePedidoDTO> detalles;

    public PedidoRequestDTO() {}

    // Getters y Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getSedeId() { return sedeId; }
    public void setSedeId(Long sedeId) { this.sedeId = sedeId; }

    public String getMetodoEntrega() { return metodoEntrega; }
    public void setMetodoEntrega(String metodoEntrega) { this.metodoEntrega = metodoEntrega; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public LocalDateTime getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(LocalDateTime fechaProgramada) { this.fechaProgramada = fechaProgramada; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getDescuento() { return descuento; }
    public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }

    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }

    public List<DetallePedidoDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedidoDTO> detalles) { this.detalles = detalles; }

    public static class DetallePedidoDTO {
        private Long productoId;
        private Integer cantidad;
        private BigDecimal precioUnitario;

        public DetallePedidoDTO() {}

        public Long getProductoId() { return productoId; }
        public void setProductoId(Long productoId) { this.productoId = productoId; }

        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

        public BigDecimal getPrecioUnitario() { return precioUnitario; }
        public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    }
}
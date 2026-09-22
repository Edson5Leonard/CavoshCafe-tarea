package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entities.Pedido;
import com.example.demo.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<Pedido>> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevo = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Pedido registrado exitosamente", nuevo));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ApiResponse<List<Pedido>>> obtenerPedidosUsuario(@PathVariable Long usuarioId) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(ApiResponse.success("Pedidos del usuario recuperados", pedidos));
    }

    @GetMapping("/{id}/estado")
    public ResponseEntity<ApiResponse<String>> consultarEstado(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(p -> ResponseEntity.ok(ApiResponse.success("Estado del pedido", p.getEstado())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error(404, "Pedido no encontrado", "No existe un pedido con la ID " + id)));
    }
}
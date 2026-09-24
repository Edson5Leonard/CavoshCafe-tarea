package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PedidoRequestDTO;
import com.example.demo.entities.Pedido;
import com.example.demo.entities.Sede;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.repositories.SedeRepository;
import com.example.demo.repositories.UsuarioRepository;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Pedido>>> obtenerTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return ResponseEntity.ok(ApiResponse.success("Lista de pedidos recuperada", pedidos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> crearPedido(@RequestBody PedidoRequestDTO req) {
        // Validar si el payload JSON vino vacío
        if (req == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(400, "Error", "El cuerpo de la petición no puede estar vacío"));
        }

        // Validar existencia de Usuario
        Usuario usuario = usuarioRepository.findById(req.getUsuarioId()).orElse(null);
        if (usuario == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(400, "Usuario no encontrado", "No existe un usuario con el ID: " + req.getUsuarioId()));
        }

        // Validar existencia de Sede
        Sede sede = sedeRepository.findById(req.getSedeId()).orElse(null);
        if (sede == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(400, "Sede no encontrada", "No existe una sede con el ID: " + req.getSedeId()));
        }

        // Mapear campos al objeto Pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setSede(sede);
        pedido.setMetodoEntrega(req.getMetodoEntrega());
        pedido.setMetodoPago(req.getMetodoPago());
        pedido.setFechaProgramada(req.getFechaProgramada());
        pedido.setSubtotal(req.getSubtotal());
        pedido.setDescuento(req.getDescuento());
        pedido.setMontoTotal(req.getMontoTotal());
        pedido.setEstado("CREADO");

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Pedido registrado exitosamente", pedidoGuardado));
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
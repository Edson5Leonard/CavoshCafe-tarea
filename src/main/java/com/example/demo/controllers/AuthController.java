package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UsuarioDTO>> login(@RequestBody Usuario loginReq) {
        return usuarioRepository.findByEmail(loginReq.getEmail())
                .filter(u -> u.getPassword().equals(loginReq.getPassword()))
                .map(u -> ResponseEntity.ok(ApiResponse.success("Login exitoso", new UsuarioDTO(u))))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error(401, "Credenciales incorrectas", "Correo o contraseña inválidos")));
    }

    @PostMapping("/registro")
    public ResponseEntity<ApiResponse<UsuarioDTO>> registrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(400, "Error al registrar", "El correo ya existe en el sistema"));
        }
        
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Usuario registrado exitosamente", new UsuarioDTO(nuevoUsuario)));
    }
}
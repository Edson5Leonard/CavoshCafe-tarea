package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.VerificacionDTO;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService; 

    @PostMapping("/registro")
public ResponseEntity<ApiResponse<UsuarioDTO>> registrar(@RequestBody Usuario usuario) {
    if (usuarioRepository.existsByEmail(usuario.getEmail())) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(400, "Error al registrar", "El correo ya existe en el sistema"));
    }

    String codigo = String.format("%06d", new Random().nextInt(999999));
    
    usuario.setCodigoVerificacion(codigo);
    usuario.setEstadoCuenta(false); 

    
    Usuario nuevoUsuario = usuarioRepository.save(usuario);

    
    try {
        emailService.enviarCodigoVerificacion(usuario.getEmail(), codigo);
    } catch (Exception e) {
        System.err.println("No se pudo enviar el correo: " + e.getMessage());
    }

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.created("Usuario registrado exitosamente. Se envió un código a tu correo.", new UsuarioDTO(nuevoUsuario)));
}

    @PostMapping("/validar-codigo")
    public ResponseEntity<ApiResponse<String>> validarCodigo(@RequestBody VerificacionDTO req) {
        return usuarioRepository.findByEmail(req.getEmail())
                .map(usuario -> {
                    if (usuario.getCodigoVerificacion() != null && usuario.getCodigoVerificacion().equals(req.getCodigo())) {
                        usuario.setEstadoCuenta(true);
                        usuario.setCodigoVerificacion(null); // Limpiar el código una vez activado
                        usuarioRepository.save(usuario);
                        return ResponseEntity.ok(ApiResponse.success("Cuenta verificada exitosamente", "OK"));
                    } else {
                        return ResponseEntity.badRequest()
                                .body(ApiResponse.<String>error(400, "Código inválido", "El código ingresado es incorrecto"));
                    }
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error(404, "Usuario no encontrado", "No existe un usuario con dicho correo")));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UsuarioDTO>> login(@RequestBody Usuario loginReq) {
        return usuarioRepository.findByEmail(loginReq.getEmail())
                .filter(u -> u.getPassword().equals(loginReq.getPassword()))
                .map(u -> {
                    if (Boolean.FALSE.equals(u.getEstadoCuenta())) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                .body(ApiResponse.<UsuarioDTO>error(403, "Cuenta no verificada", "Debe verificar su cuenta con el código enviado a su correo"));
                    }
                    return ResponseEntity.ok(ApiResponse.success("Login exitoso", new UsuarioDTO(u)));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error(401, "Credenciales incorrectas", "Correo o contraseña inválidos")));
    }
}
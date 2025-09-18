package pe.insalud.gestion_atenciones.interfaces.rest.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.insalud.gestion_atenciones.application.internal.outboundservices.JwtService;
import pe.insalud.gestion_atenciones.interfaces.rest.transform.JwtResponse;
import pe.insalud.gestion_atenciones.interfaces.rest.transform.LoginRequest;

/**
 * Controlador REST para autenticación de usuarios
 * Expone endpoints relacionados con login y emisión de JWT
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthResource {

    private final JwtService jwtService;

    /**
     * Endpoint de login.
     * Recibe email y contraseña en el body, autentica al usuario
     * y devuelve un token JWT válido para futuras solicitudes
     *
     * @param request objeto con email y password
     * @return JWT en respuesta, envuelto en JwtResponse
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Autentica el usuario y genera un token JWT
        String token = jwtService.authenticateAndGenerateToken(request.email(), request.password());
        // Devuelve el token en formato JSON
        return ResponseEntity.ok(new JwtResponse(token));
    }
    // Falto un sign-up
}

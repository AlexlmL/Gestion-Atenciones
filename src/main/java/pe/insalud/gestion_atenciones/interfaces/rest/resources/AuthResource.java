package pe.insalud.gestion_atenciones.interfaces.rest.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.insalud.gestion_atenciones.application.internal.outboundservices.JwtService;
import pe.insalud.gestion_atenciones.interfaces.rest.transform.JwtResponse;
import pe.insalud.gestion_atenciones.interfaces.rest.transform.LoginRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthResource {

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Aquí delegas a JwtService la validación de credenciales y generación del token
        String token = jwtService.authenticateAndGenerateToken(request.email(), request.password());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}

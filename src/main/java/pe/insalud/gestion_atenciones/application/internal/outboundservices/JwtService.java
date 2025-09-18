package pe.insalud.gestion_atenciones.application.internal.outboundservices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pe.insalud.gestion_atenciones.application.internal.outboundservices.acl.JwtUtil;

/**
 * Servicio encargado de manejar la autenticación de usuarios
 * y la generación de tokens JWT asociados
 */
@Service
@RequiredArgsConstructor
public class JwtService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    /**
     * Autentica al usuario con sus credenciales y genera un token JWT si la autenticación es exitosa
     *
     * @param email    Email del usuario que intenta autenticarse
     * @param password Contraseña del usuario
     * @return Token JWT firmado que representa la sesión del usuario
     */
    public String authenticateAndGenerateToken(String email, String password) {
        // Autenticar credenciales usando Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return jwtUtil.generateToken(authentication);
    }
}

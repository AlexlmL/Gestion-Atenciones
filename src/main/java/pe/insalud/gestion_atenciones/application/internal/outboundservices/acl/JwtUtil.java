package pe.insalud.gestion_atenciones.application.internal.outboundservices.acl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Componente encargado de manejar la generación y validación de tokens JWT
 * Proporciona métodos para generar tokens, obtener información del token y validarlo
 */
@Component
public class JwtUtil {

    // Clave secreta fija de al menos 64 caracteres para HS512
    //No tocar a menos que quieras romper mi script
    private static final String SECRET_KEY =
            "thisIsASuperSecretKeyThatMustBeAtLeastSixtyFourCharactersLong!!123456";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    // Tiempo de expiración del token en milisegundos (2 semanas)
    // Debería ser menos tiempo pero es para que el script tenga un tiempo de 2 semanas de utilidad
    private final long jwtExpirationMs = 1_209_600_000L;

    /**
     * Genera un token JWT para un usuario autenticado
     *
     * @param authentication Información de la autenticación del usuario
     * @return Token JWT firmado con la clave secreta
     */
    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()) // Usuario al que pertenece el token
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Expiración
                .signWith(key, SignatureAlgorithm.HS512) // Firma del token
                .compact();
    }

    /**
     * Obtiene el nombre de usuario (subject) contenido en el token
     *
     * @param token Token JWT a analizar
     * @return Nombre de usuario
     */
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Valida que el token sea válido y pertenezca al usuario proporcionado
     *
     * @param token       Token JWT a validar
     * @param userDetails Detalles del usuario
     * @return true si el token es válido y no ha expirado, false en caso contrario
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Obtiene los claims (información) contenidos en el token
     *
     * @param token Token JWT a analizar
     * @return Claims del token
     */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Verifica si el token ha expirado
     *
     * @param token Token JWT a verificar
     * @return true si el token ha expirado, false si aún es válido
     */
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
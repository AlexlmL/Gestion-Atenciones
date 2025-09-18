package pe.insalud.gestion_atenciones.interfaces.rest.transform;

/**
 * DTO para encapsular la respuesta JWT tras un login exitoso
 * Contiene únicamente el token generado que será usado para autenticación en llamadas subsecuentes
 */
public record JwtResponse(String token) {}

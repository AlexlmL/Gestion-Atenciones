package pe.insalud.gestion_atenciones.interfaces.rest.transform;

/**
 * DTO usado para el login de usuarios
 * Contiene el email y la contraseña que serán enviados al endpoint de autenticación
 */
public record LoginRequest(String email, String password) {}

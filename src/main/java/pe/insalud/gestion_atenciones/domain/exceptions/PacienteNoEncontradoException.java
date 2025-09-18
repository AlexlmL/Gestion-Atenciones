package pe.insalud.gestion_atenciones.domain.exceptions;

/**
 * Excepción lanzada cuando se intenta acceder a un paciente
 * que no existe en la base de datos.
 * La idea era usarla para más features del AdminPanel
 */
public class PacienteNoEncontradoException extends RuntimeException {
    public PacienteNoEncontradoException(Long id) {
        super("Paciente con id " + id + " no encontrado");
    }
}

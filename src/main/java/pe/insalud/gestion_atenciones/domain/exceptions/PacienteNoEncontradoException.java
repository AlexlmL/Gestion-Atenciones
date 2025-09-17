package pe.insalud.gestion_atenciones.domain.exceptions;

public class PacienteNoEncontradoException extends RuntimeException {
    public PacienteNoEncontradoException(Long id) {
        super("Paciente con id " + id + " no encontrado");
    }
}

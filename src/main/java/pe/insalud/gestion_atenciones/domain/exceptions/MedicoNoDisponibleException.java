package pe.insalud.gestion_atenciones.domain.exceptions;

public class MedicoNoDisponibleException extends RuntimeException {
  public MedicoNoDisponibleException(Long id) {
    super("Médico con id " + id + " no disponible");
  }
}

package pe.insalud.gestion_atenciones.domain.exceptions;

/**
 * Excepción lanzada cuando se intenta asignar un médico a una atención
 * pero el médico no está disponible. Por si se agregaba horas y sistema de horarios
 * ¿En qué pensaba el martes?
 */
public class MedicoNoDisponibleException extends RuntimeException {
  public MedicoNoDisponibleException(Long id) {
    super("Médico con id " + id + " no disponible");
  }
}

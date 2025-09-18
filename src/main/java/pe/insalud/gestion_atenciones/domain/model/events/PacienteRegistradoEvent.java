package pe.insalud.gestion_atenciones.domain.model.events;

/**
 * Evento que se dispara cuando se registra un nuevo paciente en el sistema
 * Contiene información relevante para notificar a otros componentes sobre el registro
 * Para el log-in que no hice
 *
 * @param pacienteId Id del paciente registrado
 * @param email Email del paciente registrado
 */
public record PacienteRegistradoEvent(Long pacienteId, String email) {}

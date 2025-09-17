package pe.insalud.gestion_atenciones.domain.model.events;

public record PacienteRegistradoEvent(Long pacienteId, String email) {}

package pe.insalud.gestion_atenciones.domain.model.commands;

public record CrearAtencionCommand(Long pacienteId, Long medicoId, String motivo) {}

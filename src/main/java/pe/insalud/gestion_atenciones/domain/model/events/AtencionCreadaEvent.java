package pe.insalud.gestion_atenciones.domain.model.events;

public record AtencionCreadaEvent(Long atencionId, Long pacienteId, Long medicoId) {}

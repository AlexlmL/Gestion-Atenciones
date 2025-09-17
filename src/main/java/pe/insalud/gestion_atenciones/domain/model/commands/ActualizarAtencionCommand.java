package pe.insalud.gestion_atenciones.domain.model.commands;

public record ActualizarAtencionCommand(Long atencionId, String motivo, Boolean estado) {}

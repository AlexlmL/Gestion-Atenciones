package pe.insalud.gestion_atenciones.domain.model.commands;

import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;

public record ActualizarAtencionCommand(Long atencionId, String motivo, Estado estado) {}

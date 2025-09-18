package pe.insalud.gestion_atenciones.domain.model.commands;

import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;

import java.time.LocalDate;

public record ActualizarAtencionCommand(Long atencionId, String motivo, Estado estado, Long pacienteId,
                                        Long medicoId, LocalDate fecha) {}

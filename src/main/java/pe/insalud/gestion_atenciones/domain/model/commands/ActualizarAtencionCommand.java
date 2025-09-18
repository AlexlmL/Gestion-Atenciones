package pe.insalud.gestion_atenciones.domain.model.commands;

import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;

import java.time.LocalDate;
/**
 * Comando para actualizar una atención médica
 * Contiene los datos opcionales que se pueden modificar de una atención existente
 *
 * @param atencionId Id de la atención que se desea actualizar (obligatorio)
 * @param motivo Motivo de la atención (obligatorio)
 * @param estado Estado de la atención (obligatorio)
 * @param pacienteId Id del paciente asociado (obligatorio)
 * @param medicoId Id del médico asociado (obligatorio
 * @param fecha Fecha de la atención (obligatorio)
 */
public record ActualizarAtencionCommand(Long atencionId, String motivo, Estado estado, Long pacienteId,
                                        Long medicoId, LocalDate fecha) {}

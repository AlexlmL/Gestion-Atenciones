package pe.insalud.gestion_atenciones.domain.model.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
/**
 * Comando para crear una nueva atención médica
 * Contiene los datos mínimos requeridos para registrar una atención
 *
 * Validaciones:
 * -
 * - medicoId: obligatorio
 * - motivo: no puede estar vacío
 * - fecha: obligatorio
 *
 * @param pacienteId Id del paciente al que se asigna la atención
 * @param medicoId Id del médico que atenderá la atención
 * @param motivo Motivo de la atención
 * @param fecha Fecha en la que se realizará la atención
 */
public record CrearAtencionCommand(
        @NotNull(message = "El paciente es obligatorio")
        Long pacienteId,

        @NotNull(message = "El médico es obligatorio")
        Long medicoId,

        @NotBlank(message = "El motivo no puede estar vacío")
        String motivo,

        @NotNull(message = "La fecha es obligatoria")
        LocalDate fecha
) {}

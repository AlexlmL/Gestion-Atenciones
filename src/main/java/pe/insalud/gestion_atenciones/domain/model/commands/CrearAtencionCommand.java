package pe.insalud.gestion_atenciones.domain.model.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearAtencionCommand(
        @NotNull(message = "El paciente es obligatorio")
        Long pacienteId,

        @NotNull(message = "El médico es obligatorio")
        Long medicoId,

        @NotBlank(message = "El motivo no puede estar vacío")
        String motivo
) {}

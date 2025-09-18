package pe.insalud.gestion_atenciones.interfaces.rest.transform;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;

/**
 * DTO para exponer información de pacientes a la capa RES
 * Contiene el id, nombre, email y rol del paciente
 */
@Data
@AllArgsConstructor
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String email; // String simple
    private String rol;

    /**
     * Convierte un objeto Paciente de la capa de dominio a un DTO
     * @param paciente entidad Paciente
     * @return PacienteDTO con id, nombre, email y rol
     */
    public static PacienteDTO from(Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail() != null ? paciente.getEmail().getValue() : null,
                paciente.getRol()
        );
    }
}


package pe.insalud.gestion_atenciones.interfaces.rest.transform;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;

@Data
@AllArgsConstructor
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String email; // String simple
    private String rol;

    public static PacienteDTO from(Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail() != null ? paciente.getEmail().getValue() : null,
                paciente.getRol()
        );
    }
}


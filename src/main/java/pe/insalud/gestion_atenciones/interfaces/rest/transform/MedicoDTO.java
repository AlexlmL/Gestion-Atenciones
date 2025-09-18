package pe.insalud.gestion_atenciones.interfaces.rest.transform;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO para exponer información de médicos a la capa REST
 * Contiene el id del médico, su nombre y la lista de especialidades asociadas
 */
@Data
@AllArgsConstructor
public class MedicoDTO {
    private Long id;
    private String nombre;
    private List<String> especialidades;

    /**
     * Convierte un objeto Medico de la capa de dominio a un DTO
     * @param medico entidad Medico
     * @return MedicoDTO con id, nombre y nombres de especialidades
     */
    public static MedicoDTO from(Medico medico) {
        List<String> esp = medico.getEspecialidades() != null
                ? medico.getEspecialidades().stream()
                .map(e -> e.getNombre())
                .collect(Collectors.toList())
                : List.of();
        return new MedicoDTO(medico.getId(), medico.getNombre(), esp);
    }
}


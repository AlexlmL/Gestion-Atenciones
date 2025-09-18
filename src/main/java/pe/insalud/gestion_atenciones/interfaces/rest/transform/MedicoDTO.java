package pe.insalud.gestion_atenciones.interfaces.rest.transform;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class MedicoDTO {
    private Long id;
    private String nombre;
    private List<String> especialidades;

    public static MedicoDTO from(Medico medico) {
        List<String> esp = medico.getEspecialidades() != null
                ? medico.getEspecialidades().stream()
                .map(e -> e.getNombre())
                .collect(Collectors.toList())
                : List.of();
        return new MedicoDTO(medico.getId(), medico.getNombre(), esp);
    }
}


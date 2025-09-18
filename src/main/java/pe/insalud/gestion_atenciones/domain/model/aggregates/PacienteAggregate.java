package pe.insalud.gestion_atenciones.domain.model.aggregates;

import lombok.Getter;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Email;
/**
 * Aggregate que representa la raíz de la entidad Paciente.
 * Permite realizar operaciones sobre el paciente de manera consistente,
 * asegurando que las reglas de negocio se apliquen correctamente
 */
@Getter
public class PacienteAggregate {

    private final Paciente paciente;

    public PacienteAggregate(Paciente paciente) {
        this.paciente = paciente;
    }

    public void cambiarEmail(Email nuevoEmail) {
        paciente.setEmail(nuevoEmail);
    }
}

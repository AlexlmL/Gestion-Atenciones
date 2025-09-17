package pe.insalud.gestion_atenciones.domain.model.aggregates;

import lombok.Getter;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Email;

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

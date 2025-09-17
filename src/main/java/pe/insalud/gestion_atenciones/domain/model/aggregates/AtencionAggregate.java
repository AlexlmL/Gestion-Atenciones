package pe.insalud.gestion_atenciones.domain.model.aggregates;

import lombok.Getter;
import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;

@Getter
public class AtencionAggregate {

    private final Atencion atencion;

    public AtencionAggregate(Atencion atencion) {
        this.atencion = atencion;
    }

    public void asignarPaciente(Paciente paciente) {
        atencion.setPaciente(paciente);
    }

    public void asignarMedico(Medico medico) {
        atencion.setMedico(medico);
    }
}

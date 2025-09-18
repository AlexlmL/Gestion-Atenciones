package pe.insalud.gestion_atenciones.domain.model.aggregates;

import lombok.Getter;
import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;
/**
 * Aggregate que representa la raíz de la entidad Atencion
 * Permite realizar operaciones sobre la atención y sus relaciones
 * con Paciente y Medico de manera consistente
 * La idea para este mismo era darle más features para la vista de Paciente
 * Como cambiar su fecha de la cita o hasta el doctor o hace un minipanel
 * Nuevamente
 * ¿En qué pensaba el martes?
 */
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

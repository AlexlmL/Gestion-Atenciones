package pe.insalud.gestion_atenciones.domain.services;

import org.springframework.stereotype.Service;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;
/**
 * Servicio de dominio encargado de validar reglas de negocio relacionadas
 * con las atenciones médicas
 * Para dar de baja a los pacientes, feature no implementado en el adminpanel
 *
 * Actualmente valida que tanto paciente como médico estén activos antes de crear
 * una nueva atención
 */
@Service
public class AtencionDomainService {

    /**
     * Valida que el paciente y el médico estén activos
     *
     * @param paciente Paciente que se va a asignar a la atención
     * @param medico Médico que se va a asignar a la atención
     * @throws IllegalStateException si el paciente o médico no están activos
     */
    public void validarNuevaAtencion(Paciente paciente, Medico medico) {
        if (paciente == null || Boolean.FALSE.equals(paciente.getEstado())) {
            throw new IllegalStateException("El paciente no está activo");
        }
        if (medico == null || Boolean.FALSE.equals(medico.getEstado())) {
            throw new IllegalStateException("El médico no está activo");
        }
    }
}

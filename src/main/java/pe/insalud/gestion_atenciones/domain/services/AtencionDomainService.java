package pe.insalud.gestion_atenciones.domain.services;

import org.springframework.stereotype.Service;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;

@Service
public class AtencionDomainService {

    public void validarNuevaAtencion(Paciente paciente, Medico medico) {
        if (paciente == null || Boolean.FALSE.equals(paciente.getEstado())) {
            throw new IllegalStateException("El paciente no está activo");
        }
        if (medico == null || Boolean.FALSE.equals(medico.getEstado())) {
            throw new IllegalStateException("El médico no está activo");
        }
    }
}

package pe.insalud.gestion_atenciones.interfaces.rest.transform;

import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;

public record AtencionResourceFromEntity(Long id, String motivo, String paciente, String medico) {

    public static AtencionResourceFromEntity fromEntity(Atencion atencion) {
        return new AtencionResourceFromEntity(
                atencion.getId(),
                atencion.getMotivo(),
                atencion.getPaciente().getNombre(),
                atencion.getMedico().getNombre()
        );
    }
}
package pe.insalud.gestion_atenciones.interfaces.rest.transform;

import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;

/**
 * DTO (Data Transfer Object) para exponer información de Atenciones a través de la API REST
 * Contiene únicamente los campos necesarios para la vista, evitando exponer toda la entidad
 * Esto sería un ambiente de producción
 */
public record AtencionResourceFromEntity(Long id, String motivo, String paciente, String medico) {

    /**
     * Convierte una entidad Atencion a su correspondiente DTO
     *
     * @param atencion Entidad Atencion
     * @return DTO AtencionResourceFromEntity
     */
    public static AtencionResourceFromEntity fromEntity(Atencion atencion) {
        return new AtencionResourceFromEntity(
                atencion.getId(),
                atencion.getMotivo(),
                atencion.getPaciente().getNombre(),
                atencion.getMedico().getNombre()
        );
    }
}
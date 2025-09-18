package pe.insalud.gestion_atenciones.domain.model.commands;

/**
 * Comando para eliminar una atención existente.
 * Contiene únicamente el identificador de la atención que se desea eliminar.
 *
 * @param atencionId Id de la atención a eliminar.
 */
public record EliminarAtencionCommand(Long atencionId) { }

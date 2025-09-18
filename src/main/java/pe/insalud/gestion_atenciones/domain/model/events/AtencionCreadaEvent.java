package pe.insalud.gestion_atenciones.domain.model.events;

/**
 * Evento que se dispara cuando se crea una nueva atención médica
 * Contiene la información necesaria para notificar a otros componentes
 * sobre la creación de la atención
 *
 * @param atencionId Id de la atención creada
 * @param pacienteId Id del paciente asociado a la atención
 * @param medicoId Id del médico asignado a la atención
 */
public record AtencionCreadaEvent(Long atencionId, Long pacienteId, Long medicoId) {}

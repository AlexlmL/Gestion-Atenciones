package pe.insalud.gestion_atenciones.domain.model.queries;
/**
 * Query para obtener todas las atenciones asociadas a un paciente,
 * a partir de su email
 *
 * @param email Email del paciente cuyas atenciones se desean consultar
 */
public record ObtenerAtencionesPorPacienteEmailQuery(String email) { }

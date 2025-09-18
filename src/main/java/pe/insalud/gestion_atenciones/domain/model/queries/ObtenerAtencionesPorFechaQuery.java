package pe.insalud.gestion_atenciones.domain.model.queries;

import java.time.LocalDate;
/**
 * Query para obtener todas las atenciones que ocurren en una fecha específica
 * Al final me olvidé de que estuvo aquí
 * Si me pongo a pensar en queries se hacen cientos
 *
 * @param fecha Fecha de las atenciones a consultar
 */
public record ObtenerAtencionesPorFechaQuery(LocalDate fecha) {}

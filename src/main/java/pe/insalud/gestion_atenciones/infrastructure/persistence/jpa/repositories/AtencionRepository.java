package pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio JPA para la entidad Atencion
 * Proporciona métodos CRUD y consultas personalizadas para filtrar atenciones
 * por fecha, paciente o médico
 */
public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    /**
     * Obtiene todas las atenciones cuyo campo 'fecha' esté entre
     * los valores proporcionados.
     *
     * @param inicio Fecha/hora de inicio
     * @param fin Fecha/hora de fin
     * @return Lista de atenciones en el rango de fechas
     */
    @Query("SELECT a FROM Atencion a WHERE a.fecha BETWEEN :inicio AND :fin")
    List<Atencion> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    /**
     * Obtiene todas las atenciones asociadas a un médico específico
     *
     * @param medicoId Id del médico
     * @return Lista de atenciones del médico
     */
    @Query("SELECT a FROM Atencion a WHERE a.medico.id = :medicoId")
    List<Atencion> findByMedico(Long medicoId);

    /**
     * Obtiene todas las atenciones asociadas a un paciente específico
     *
     * @param pacienteId Id del paciente
     * @return Lista de atenciones del paciente
     */
    @Query("SELECT a FROM Atencion a WHERE a.paciente.id = :pacienteId")
    List<Atencion> findByPaciente(Long pacienteId);
}

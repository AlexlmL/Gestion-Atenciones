package pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;

import java.util.List;

/**
 * Repositorio JPA para la entidad Medico
 * Proporciona métodos CRUD estándar y consultas personalizadas relacionadas con médicos
 */
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    /**
     * Obtiene todos los médicos de la base de datos
     *
     * @return Lista de todos los médicos
     */
    @Query("SELECT m FROM Medico m")
    List<Medico> findAllMedicos();
    // Faltaron métodos crud para los medicos
}

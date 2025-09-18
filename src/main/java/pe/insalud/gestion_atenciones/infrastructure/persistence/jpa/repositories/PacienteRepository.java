package pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Email;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Paciente
 * Proporciona métodos CRUD estándar y consultas personalizadas relacionadas con pacientes
 */
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    /**
     * Busca un paciente por su email
     *
     * @param email Email del paciente a buscar
     * @return Optional con el paciente si existe
     */
    Optional<Paciente> findByEmail(Email email);

    /**
     * Obtiene todos los pacientes de la base de datos
     *
     * @return Lista de todos los pacientes
     */
    @Query("SELECT p FROM Paciente p")
    List<Paciente> findAllPacientes();
}



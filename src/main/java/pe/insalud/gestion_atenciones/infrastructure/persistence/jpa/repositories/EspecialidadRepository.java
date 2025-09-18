package pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.insalud.gestion_atenciones.domain.model.entities.Especialidad;
/**
 * Repositorio JPA para la entidad Especialidad
 * Proporciona métodos CRUD estándar para manejar especialidades médicas
 */
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    // No se requieren consultas personalizadas por el momento. Iban para la manipulación de especialidades
    // Para el rol MEDICO
}

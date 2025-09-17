package pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.insalud.gestion_atenciones.domain.model.entities.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
}

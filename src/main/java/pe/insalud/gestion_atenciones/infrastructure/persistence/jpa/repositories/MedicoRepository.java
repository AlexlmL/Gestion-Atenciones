package pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}

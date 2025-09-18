package pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query("SELECT m FROM Medico m")
    List<Medico> findAllMedicos();
}

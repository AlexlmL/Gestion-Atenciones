package pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;

import java.time.LocalDateTime;
import java.util.List;

public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    @Query("SELECT a FROM Atencion a WHERE a.fecha BETWEEN :inicio AND :fin")
    List<Atencion> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query("SELECT a FROM Atencion a WHERE a.medico.id = :medicoId")
    List<Atencion> findByMedico(Long medicoId);

    @Query("SELECT a FROM Atencion a WHERE a.paciente.id = :pacienteId")
    List<Atencion> findByPaciente(Long pacienteId);
}

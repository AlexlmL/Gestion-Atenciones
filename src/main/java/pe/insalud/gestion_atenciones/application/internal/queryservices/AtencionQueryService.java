package pe.insalud.gestion_atenciones.application.internal.queryservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;
import pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories.AtencionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtencionQueryService {

    private final AtencionRepository atencionRepository;

    public List<Atencion> getAll() {
        return atencionRepository.findAll();
    }

    public List<Atencion> getByPaciente(Long pacienteId) {
        return atencionRepository.findByPaciente(pacienteId);
    }

    public List<Atencion> getByMedico(Long medicoId) {
        return atencionRepository.findByMedico(medicoId);
    }

    public List<Atencion> getByFecha(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(23, 59, 59);
        return atencionRepository.findByFechaBetween(inicio, fin);
    }
}

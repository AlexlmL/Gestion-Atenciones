package pe.insalud.gestion_atenciones.application.internal.queryservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;
import pe.insalud.gestion_atenciones.domain.model.queries.ObtenerAtencionesPorPacienteEmailQuery;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Email;
import pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories.AtencionRepository;
import pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories.MedicoRepository;
import pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories.PacienteRepository;
import pe.insalud.gestion_atenciones.interfaces.rest.transform.MedicoDTO;
import pe.insalud.gestion_atenciones.interfaces.rest.transform.PacienteDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de manejar consultas (queries) relacionadas con Atenciones
 * Pacientes y Médicos
 * Proporciona métodos para obtener listas y búsquedas específicas
 * Escenciales para la consultas y los features del frontend
 */
@Service
@RequiredArgsConstructor
public class AtencionQueryService {

    private final AtencionRepository atencionRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    /**
     * Obtiene todas las atenciones registradas.
     *
     * @return Lista de todas las atenciones.
     */
    public List<Atencion> getAll() {
        return atencionRepository.findAll();
    }

    /**
     * Obtiene todas las atenciones de un paciente específico
     *
     * @param pacienteId Id del paciente
     * @return Lista de atenciones del paciente
     * @throws IllegalArgumentException si el id del paciente es nulo
     */
    public List<Atencion> getByPaciente(Long pacienteId) {
        if (pacienteId == null) {
            throw new IllegalArgumentException("El id del paciente no puede ser nulo");
        }
        return atencionRepository.findByPaciente(pacienteId);
    }

    /**
     * Obtiene todas las atenciones de un médico específico
     *
     * @param medicoId Id del médico
     * @return Lista de atenciones del médico
     * @throws IllegalArgumentException si el id del médico es nulo
     */
    public List<Atencion> getByMedico(Long medicoId) {
        if (medicoId == null) {
            throw new IllegalArgumentException("El id del médico no puede ser nulo");
        }
        return atencionRepository.findByMedico(medicoId);
    }

    /**
     * Obtiene todas las atenciones en una fecha específica
     *
     * @param fecha Fecha a filtrar
     * @return Lista de atenciones de la fecha indicada
     * @throws IllegalArgumentException si la fecha es nula
     */
    public List<Atencion> getByFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(23, 59, 59);
        return atencionRepository.findByFechaBetween(inicio, fin);
    }

    /**
     * Obtiene las atenciones de un paciente a partir de su email
     *
     * @param query Objeto que contiene el email del paciente
     * @return Lista de atenciones del paciente
     * @throws IllegalArgumentException si el email es nulo o vacío
     * @throws RuntimeException si no se encuentra un paciente con ese email
     */
    public List<Atencion> getByPacienteEmail(ObtenerAtencionesPorPacienteEmailQuery query) {
        if (query == null || query.email() == null || query.email().isBlank()) {
            throw new IllegalArgumentException("El email del paciente no puede ser nulo o vacío");
        }
        var paciente = pacienteRepository.findByEmail(new Email(query.email()))
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con email: " + query.email()));
        return getByPaciente(paciente.getId());
    }
    /**
     * Obtiene todos los pacientes en formato DTO
     * Ideal para entities con valueobjects como atributos
     *
     * @return Lista de pacientes como PacienteDTO
     */
    public List<PacienteDTO> getAllPacientes() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteDTO::from)
                .toList();
    }

    /**
     * Obtiene todos los médicos en formato DTO.
     *
     * @return Lista de médicos como MedicoDTO.
     */
    public List<MedicoDTO> getAllMedicos() {
        return medicoRepository.findAll()
                .stream()
                .map(MedicoDTO::from)
                .toList();
    }
    /**
     * Obtiene una atención por su id
     * Hecho solamente para consistencia del PUT en el Frontend
     *
     * @param id Id de la atención
     * @return Optional con la atención si existe, vacío en caso contrario
     */
    public Optional<Atencion> getById(Long id) {
        return atencionRepository.findById(id);
    }

}

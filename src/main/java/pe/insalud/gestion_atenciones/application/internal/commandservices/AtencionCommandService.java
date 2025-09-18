package pe.insalud.gestion_atenciones.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;
import pe.insalud.gestion_atenciones.domain.model.commands.ActualizarAtencionCommand;
import pe.insalud.gestion_atenciones.domain.model.commands.CrearAtencionCommand;
import pe.insalud.gestion_atenciones.domain.model.commands.EliminarAtencionCommand;
import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;
import pe.insalud.gestion_atenciones.domain.model.entities.Medico;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;
import pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories.AtencionRepository;
import pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories.MedicoRepository;
import pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories.PacienteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * Servicio encargado de manejar comandos relacionados con Atenciones.
 * Proporciona operaciones de creación, actualización y eliminación de Atenciones.
 */
@Service
@RequiredArgsConstructor
public class AtencionCommandService {

    private final AtencionRepository atencionRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    /**
     * Maneja la creación de una nueva atención
     *
     * @param command Comando que contiene los datos necesarios para crear la atención
     * @return La atención creada y persistida en la base de datos
     */
    public Atencion handle(CrearAtencionCommand command) {
        // Buscar paciente por id
        Paciente paciente = pacienteRepository.findById(command.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        //Buscar médico por id
        Medico medico = medicoRepository.findById(command.medicoId())
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        // Validar que la fecha no sea futura
        if (command.fecha().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de la atención no puede ser futura");
        }
        //Crea la atención y asigna valores
        Atencion atencion = new Atencion();
        atencion.setFecha(LocalDateTime.now());
        atencion.setMotivo(command.motivo());
        atencion.setPaciente(paciente);
        atencion.setMedico(medico);
        atencion.setEstado(new Estado(true));

        return atencionRepository.save(atencion);
    }

    /**
     * Maneja la actualización de una atención existente
     *
     * @param command Comando que contiene los datos a actualizar
     * @return La atención actualizada y persistida en la base de datos
     */
    public Atencion handle(ActualizarAtencionCommand command) {

        if (command.atencionId() == null) {
            throw new IllegalArgumentException("El id de la atención no puede ser nulo");
        }
        // Buscar atención por id
        Atencion atencion = atencionRepository.findById(command.atencionId())
                .orElseThrow(() -> new IllegalArgumentException("Atención no encontrada con id: " + command.atencionId()));
        // Actualizar motivo
        if (command.motivo() != null) {
            atencion.setMotivo(command.motivo());
        }
        // Actualizar estado
        if (command.estado() != null) {
            atencion.setEstado(command.estado());
        }
        // Actualizar paciente si se envía id
        if (command.pacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(command.pacienteId())
                    .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado con id: " + command.pacienteId()));
            atencion.setPaciente(paciente);
        }
        // Actualizar estado
        if (command.estado() != null) {
            atencion.setEstado(command.estado());
        }
        // Actualizar médico si se envía id
        if (command.medicoId() != null) {
            Medico medico = medicoRepository.findById(command.medicoId())
                    .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado con id: " + command.medicoId()));
            atencion.setMedico(medico);
        }
        if (command.fecha() != null) {
            atencion.setFecha(command.fecha().atStartOfDay());
        }
        return atencionRepository.save(atencion);
    }
    /**
     * Maneja la eliminación de una atención existente
     *
     * @param command Comando que contiene el id de la atención a eliminar
     */
    public void handle(EliminarAtencionCommand command) {
        if (command.atencionId() == null) {
            throw new IllegalArgumentException("El id de la atención no puede ser nulo");
        }
        // Verificar que la atención existe
        if (!atencionRepository.existsById(command.atencionId())) {
            throw new RuntimeException("Atención no encontrada con id: " + command.atencionId());
        }
        atencionRepository.deleteById(command.atencionId());
    }
}

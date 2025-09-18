package pe.insalud.gestion_atenciones.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AtencionCommandService {

    private final AtencionRepository atencionRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public Atencion handle(CrearAtencionCommand command) {
        Paciente paciente = pacienteRepository.findById(command.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Medico medico = medicoRepository.findById(command.medicoId())
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));

        Atencion atencion = new Atencion();
        atencion.setFecha(LocalDateTime.now());
        atencion.setMotivo(command.motivo());
        atencion.setPaciente(paciente);
        atencion.setMedico(medico);
        atencion.setEstado(new Estado(true));

        return atencionRepository.save(atencion);
    }
    public Atencion handle(ActualizarAtencionCommand command) {

        if (command.atencionId() == null) {
            throw new IllegalArgumentException("El id de la atención no puede ser nulo");
        }

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

        // Actualizar médico si se envía id
        if (command.medicoId() != null) {
            Medico medico = medicoRepository.findById(command.medicoId())
                    .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado con id: " + command.medicoId()));
            atencion.setMedico(medico);
        }
        if (command.fecha() != null) {
            atencion.setFecha(command.fecha().atStartOfDay()); // <-- actualizamos la fecha
        }
        return atencionRepository.save(atencion);
    }

    public void handle(EliminarAtencionCommand command) {
        if (command.atencionId() == null) {
            throw new IllegalArgumentException("El id de la atención no puede ser nulo");
        }
        if (!atencionRepository.existsById(command.atencionId())) {
            throw new RuntimeException("Atención no encontrada con id: " + command.atencionId());
        }
        atencionRepository.deleteById(command.atencionId());
    }
}

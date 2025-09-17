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
        Atencion atencion = atencionRepository.findById(command.atencionId())
                .orElseThrow(() -> new RuntimeException("Atención no encontrada con id: " + command.atencionId()));

        if (command.motivo() != null) {
            atencion.setMotivo(command.motivo());
        }
        if (command.estado() != null) {
            atencion.setEstado(command.estado());
        }

        return atencionRepository.save(atencion);
    }
    public void handle(EliminarAtencionCommand command) {
        if (!atencionRepository.existsById(command.atencionId())) {
            throw new RuntimeException("Atención no encontrada con id: " + command.atencionId());
        }
        atencionRepository.deleteById(command.atencionId());
    }
}

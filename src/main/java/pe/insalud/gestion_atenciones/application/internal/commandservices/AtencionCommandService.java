package pe.insalud.gestion_atenciones.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.insalud.gestion_atenciones.domain.model.commands.CrearAtencionCommand;
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
}

package pe.insalud.gestion_atenciones.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.insalud.gestion_atenciones.domain.model.commands.CrearPacienteCommand;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;
import pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories.PacienteRepository;


@Service
@RequiredArgsConstructor
public class PacienteCommandService {

    private final PacienteRepository pacienteRepository;

    public Paciente handle(CrearPacienteCommand command) {
        Paciente paciente = new Paciente();
        paciente.setNombre(command.nombre());
        paciente.setEmail(command.email());
        paciente.setContrasena(command.contrasena());
        paciente.setEstado(new Estado(true));
        return pacienteRepository.save(paciente);
    }
}

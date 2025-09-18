package pe.insalud.gestion_atenciones.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.insalud.gestion_atenciones.domain.model.commands.CrearPacienteCommand;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;
import pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories.PacienteRepository;

/**
 * Servicio encargado de manejar comandos relacionados con Pacientes
 * Actualmente proporciona operaciones de creación de Pacientes
 * Se usaría para el sign-up
 */
@Service
@RequiredArgsConstructor
public class PacienteCommandService {

    private final PacienteRepository pacienteRepository;

    /**
     * Maneja la creación de un nuevo paciente.
     *
     * @param command Comando que contiene los datos necesarios para crear el paciente
     * @return El paciente creado y persistido en la base de datos
     */
    public Paciente handle(CrearPacienteCommand command) {
        // Crear un nuevo objeto Paciente y asignar valores
        Paciente paciente = new Paciente();
        paciente.setNombre(command.nombre());
        paciente.setEmail(command.email());
        paciente.setContrasena(command.contrasena());
        paciente.setEstado(new Estado(true));
        return pacienteRepository.save(paciente);
    }
}

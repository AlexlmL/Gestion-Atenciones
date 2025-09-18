package pe.insalud.gestion_atenciones.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.insalud.gestion_atenciones.domain.model.entities.Paciente;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Email;
import pe.insalud.gestion_atenciones.infrastructure.persistence.jpa.repositories.PacienteRepository;

/**
 * Servicio personalizado de Spring Security para cargar los detalles del usuario
 *
 * Implementa UserDetailsService para autenticar usuarios por su email y asignar roles
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PacienteRepository pacienteRepository;

    /**
     * Carga un usuario por su email
     *
     * @param email Email del usuario
     * @return UserDetails con username, password y roles
     * @throws UsernameNotFoundException si no se encuentra el usuario
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Paciente paciente = pacienteRepository.findByEmail(new Email(email))
                .orElseThrow(() -> new UsernameNotFoundException("No existe usuario con email: " + email));

        return User.builder()
                .username(paciente.getEmail().getValue())
                .password(paciente.getContrasena())
                .roles(paciente.getRol())
                .build();
    }
}

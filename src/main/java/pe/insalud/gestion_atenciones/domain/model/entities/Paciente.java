package pe.insalud.gestion_atenciones.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Email;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;

/**
 * Entidad que representa un paciente en el sistema
 * Contiene información personal, credenciales, estado y rol dentro del sistema
 * Usuario Default de la plataforma
 */
@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    /** Identificador único del paciente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre completo del paciente */
    private String nombre;

    /** Contraseña del paciente (almacenada encriptada) */
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    /** Email del paciente, representado como Value Object Email */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", nullable = false, unique = true))
    private Email email;

    /** Estado del paciente (activo/inactivo) */
    @Embedded
    @AttributeOverride(name = "activo", column = @Column(name = "estado"))
    private Estado estado;

    /** Rol del paciente dentro del sistema (PACIENTE, ADMIN) */
    @Column(nullable = false)
    private String rol;
}

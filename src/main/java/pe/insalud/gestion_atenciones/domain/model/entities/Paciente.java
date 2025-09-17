package pe.insalud.gestion_atenciones.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Email;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", nullable = false, unique = true))
    private Email email;

    @Embedded
    @AttributeOverride(name = "activo", column = @Column(name = "estado"))
    private Estado estado;

    @Column(nullable = false)
    private String rol;
}

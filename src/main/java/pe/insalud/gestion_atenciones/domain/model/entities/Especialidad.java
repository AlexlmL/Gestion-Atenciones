package pe.insalud.gestion_atenciones.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;

/**
 * Entidad que representa una especialidad médica
 * Contiene el nombre de la especialidad y su estado (activo/inactivo)
 * La idea detrás de ese atributo es que hay doctores que en su tiempo
 * trabajando pueden cambiar de especialidad
 */
@Entity
@Table(name = "especialidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Especialidad {

    /** Identificador único de la especialidad */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la especialidad, debe ser único y no nulo */
    @Column(unique = true, nullable = false)
    private String nombre;

    /** Estado de la especialidad (activo/inactivo) */
    @Embedded
    @AttributeOverride(name = "activo", column = @Column(name = "estado"))
    private Estado estado;
}

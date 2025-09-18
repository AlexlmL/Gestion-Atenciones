package pe.insalud.gestion_atenciones.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;

import java.util.List;

/**
 * Entidad que representa un médico en el sistema
 * Contiene información sobre el nombre, estado y las especialidades asociadas
 */
@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {
    /** Identificador único del médico */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre completo del médico */
    private String nombre;

    /** Estado del médico (activo/inactivo) */
    @Embedded
    @AttributeOverride(name = "activo", column = @Column(name = "estado"))
    private Estado estado;

    /** Especialidades asociadas al médico */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "medico_especialidad",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private List<Especialidad> especialidades;
}
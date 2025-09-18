package pe.insalud.gestion_atenciones.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import pe.insalud.gestion_atenciones.domain.model.valueobjects.Estado;

import java.time.LocalDateTime;

/**
 * Entidad que representa una atención médica en el sistema
 * Contiene información sobre la fecha, motivo, estado y las relaciones
 * con paciente y médico
 */
@Entity
@Table(name = "atenciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atencion {

    /** Identificador único de la atención */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Fecha y hora de la atención */
    private LocalDateTime fecha;

    /** Motivo de la atención */
    private String motivo;

    /** Estado de la atención (activo/inactivo, etc.) */
    @Embedded
    private Estado estado;

    /** Paciente asociado a la atención */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    /** Médico asignado a la atención */
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
}


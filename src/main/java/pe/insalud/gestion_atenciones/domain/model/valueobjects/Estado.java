package pe.insalud.gestion_atenciones.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

/**
 * Value Object que representa el estado de una entidad (activo/inactivo)
 *
 * Reglas:
 * - Si no se proporciona valor, por defecto se considera activo (true)
 * - Se puede usar como campo embebido en entidades JPA
 */
@Getter
@Embeddable
public class Estado {

    /** Valor del estado: true = activo, false = inactivo */
    private Boolean activo;

    protected Estado() { }
    /**
     * Constructor principal que asigna el estado
     *
     * @param activo Valor inicial del estado; si es null, se asigna true por defecto
     */
    public Estado(Boolean activo) {
        this.activo = activo != null ? activo : Boolean.TRUE;
    }
}

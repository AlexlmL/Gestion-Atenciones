package pe.insalud.gestion_atenciones.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

/**
 * Value Object que representa un email válido
 *
 * Reglas:
 * - Debe cumplir con un patrón básico de email
 * - Es inmutable desde fuera de la clase (solo getter público)
 *
 * Se puede usar como campo embebido en entidades JPA
 */
@Getter
@Embeddable
public class Email {

    private String value;

    /** Constructor protegido requerido por JPA */
    protected Email() { }

    /**
     * Constructor principal que valida el email
     *
     * @param value Email a asignar
     * @throws IllegalArgumentException si el email no cumple el patrón
     */
    public Email(String value) {
        if (value == null || !value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.value = value;
    }
}


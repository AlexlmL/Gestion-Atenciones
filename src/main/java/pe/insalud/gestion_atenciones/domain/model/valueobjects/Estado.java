package pe.insalud.gestion_atenciones.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Estado {

    private Boolean activo;

    protected Estado() { }

    public Estado(Boolean activo) {
        this.activo = activo != null ? activo : Boolean.TRUE;
    }
}

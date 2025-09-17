package pe.insalud.gestion_atenciones.application.internal.eventhandlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pe.insalud.gestion_atenciones.domain.model.events.AtencionCreadaEvent;

@Slf4j
@Component
public class AtencionEventHandler {

    @EventListener
    public void handle(AtencionCreadaEvent event) {
        log.info("Atención creada: {}, paciente={}, médico={}",
                event.atencionId(), event.pacienteId(), event.medicoId());
    }
}

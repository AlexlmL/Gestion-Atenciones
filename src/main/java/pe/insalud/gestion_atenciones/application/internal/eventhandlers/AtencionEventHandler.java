package pe.insalud.gestion_atenciones.application.internal.eventhandlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pe.insalud.gestion_atenciones.domain.model.events.AtencionCreadaEvent;

/**
 * EventHandler encargado de procesar eventos relacionados con Atenciones
 * Actualmente escucha eventos de tipo AtencionCreadaEvent
 */
@Slf4j
@Component
public class AtencionEventHandler {
    /**
     * Maneja el evento de creación de una atención
     * Se ejecuta automáticamente cuando se publica un AtencionCreadaEvent en el contexto de Spring
     *
     * @param event Evento que contiene información sobre la atención creada
     */
    @EventListener
    public void handle(AtencionCreadaEvent event) {
        // Loguea información relevante del evento
        log.info("Atención creada: {}, paciente={}, médico={}",
                event.atencionId(), event.pacienteId(), event.medicoId());
    }
}

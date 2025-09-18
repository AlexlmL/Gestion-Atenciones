package pe.insalud.gestion_atenciones.interfaces.rest.resources;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.insalud.gestion_atenciones.application.internal.commandservices.AtencionCommandService;
import pe.insalud.gestion_atenciones.application.internal.queryservices.AtencionQueryService;
import pe.insalud.gestion_atenciones.domain.model.commands.CrearAtencionCommand;
import pe.insalud.gestion_atenciones.domain.model.commands.ActualizarAtencionCommand;
import pe.insalud.gestion_atenciones.domain.model.commands.EliminarAtencionCommand;
import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;
import pe.insalud.gestion_atenciones.domain.model.queries.ObtenerAtencionesPorPacienteEmailQuery;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST para gestionar las Atenciones
 * Define endpoints CRUD y consultas específicas según roles
 */
@RestController
@RequestMapping("/api/atenciones")
@RequiredArgsConstructor
public class AtencionResource {

    private final AtencionCommandService atencionCommandService;
    private final AtencionQueryService atencionQueryService;

    /**
     * Obtener todas las atenciones
     * Solo accesible para ADMIN
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Atencion>> getAll() {
        return ResponseEntity.ok(atencionQueryService.getAll());
    }

    /**
     * Obtener atenciones del paciente autenticado
     * Accesible para PACIENTE y ADMIN
     */
    @GetMapping("/mias")
    @PreAuthorize("hasRole('PACIENTE') or hasRole('ADMIN')")
    public ResponseEntity<List<Atencion>> getMine() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return ResponseEntity.ok(
                atencionQueryService.getByPacienteEmail(new ObtenerAtencionesPorPacienteEmailQuery(email))
        );
    }
    /**
     * Obtener atenciones por ID de paciente
     * Solo ADMIN.
     */
    @GetMapping("/paciente/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Atencion>> getByPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(atencionQueryService.getByPaciente(id));
    }

    /**
     * Obtener atenciones por ID de médico
     * Solo ADMIN.
     */
    @GetMapping("/medico/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Atencion>> getByMedico(@PathVariable Long id) {
        return ResponseEntity.ok(atencionQueryService.getByMedico(id));
    }

    /**
     * Obtener atenciones por fecha exacta
     * Solo ADMIN.
     */
    @GetMapping("/fecha")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Atencion>> getByFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(atencionQueryService.getByFecha(fecha));
    }

    /**
     * Crear una nueva atención
     * Solo ADMIN
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Atencion> create(@Valid @RequestBody CrearAtencionCommand command) {
        return ResponseEntity.ok(atencionCommandService.handle(command));
    }
    /**
     * Obtener una atención por su ID
     * Solo ADMIN. Útil para editar en frontend
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Atencion> getAtencionById(@PathVariable Long id) {
        Atencion atencion = atencionQueryService.getById(id)
                .orElseThrow(() -> new RuntimeException("Atención no encontrada con id: " + id));
        return ResponseEntity.ok(atencion);
    }

    /**
     * Actualizar una atención existente
     * Solo ADMIN
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Atencion> update(@PathVariable Long id,
                                           @RequestBody ActualizarAtencionCommand body) {
        ActualizarAtencionCommand command = new ActualizarAtencionCommand(id, body.motivo(), body.estado(), body.pacienteId(), body.medicoId(), body.fecha());
        return ResponseEntity.ok(atencionCommandService.handle(command));
    }

    /**
     * Eliminar una atención por ID
     * Solo ADMIN
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atencionCommandService.handle(new EliminarAtencionCommand(id));
        return ResponseEntity.noContent().build();
    }
}
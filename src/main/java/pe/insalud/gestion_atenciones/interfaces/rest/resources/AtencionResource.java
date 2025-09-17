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

@RestController
@RequestMapping("/api/atenciones")
@RequiredArgsConstructor
public class AtencionResource {

    private final AtencionCommandService atencionCommandService;
    private final AtencionQueryService atencionQueryService;

    // Solo ADMIN
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Atencion>> getAll() {
        return ResponseEntity.ok(atencionQueryService.getAll());
    }

    // Solo PACIENTE autenticado
    @GetMapping("/mias")
    @PreAuthorize("hasRole('PACIENTE')")
    public ResponseEntity<List<Atencion>> getMine() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return ResponseEntity.ok(
                atencionQueryService.getByPacienteEmail(new ObtenerAtencionesPorPacienteEmailQuery(email))
        );
    }
    // Solo ADMIN: consultar por paciente (id)
    @GetMapping("/paciente/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Atencion>> getByPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(atencionQueryService.getByPaciente(id));
    }

    //Solo ADMIN: consultar por médico (id)
    @GetMapping("/medico/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Atencion>> getByMedico(@PathVariable Long id) {
        return ResponseEntity.ok(atencionQueryService.getByMedico(id));
    }

    // Solo ADMIN: consultar por fecha exacta
    @GetMapping("/fecha")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Atencion>> getByFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(atencionQueryService.getByFecha(fecha));
    }

    // Solo ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Atencion> create(@Valid @RequestBody CrearAtencionCommand command) {
        return ResponseEntity.ok(atencionCommandService.handle(command));
    }

    // Solo ADMIN
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Atencion> update(@PathVariable Long id,
                                           @RequestBody ActualizarAtencionCommand body) {
        ActualizarAtencionCommand command = new ActualizarAtencionCommand(id, body.motivo(), body.estado(), body.pacienteId(), body.medicoId());
        return ResponseEntity.ok(atencionCommandService.handle(command));
    }

    // Solo ADMIN
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atencionCommandService.handle(new EliminarAtencionCommand(id));
        return ResponseEntity.noContent().build();
    }
}
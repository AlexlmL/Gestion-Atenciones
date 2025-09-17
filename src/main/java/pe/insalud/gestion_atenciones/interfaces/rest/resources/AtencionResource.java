package pe.insalud.gestion_atenciones.interfaces.rest.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.insalud.gestion_atenciones.application.internal.commandservices.AtencionCommandService;
import pe.insalud.gestion_atenciones.application.internal.queryservices.AtencionQueryService;
import pe.insalud.gestion_atenciones.domain.model.commands.CrearAtencionCommand;
import pe.insalud.gestion_atenciones.domain.model.entities.Atencion;

import java.util.List;

@RestController
@RequestMapping("/api/atenciones")
@RequiredArgsConstructor
public class AtencionResource {

    private final AtencionCommandService atencionCommandService;
    private final AtencionQueryService atencionQueryService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Atencion>> getAll() {
        return ResponseEntity.ok(atencionQueryService.getAll());
    }

    @GetMapping("/mias")
    @PreAuthorize("hasRole('PACIENTE')")
    public ResponseEntity<List<Atencion>> getMine(@RequestParam Long pacienteId) {
        return ResponseEntity.ok(atencionQueryService.getByPaciente(pacienteId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Atencion> create(@RequestBody CrearAtencionCommand command) {
        return ResponseEntity.ok(atencionCommandService.handle(command));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Atencion> update(@PathVariable Long id, @RequestBody CrearAtencionCommand command) {
        return ResponseEntity.ok(atencionCommandService.handle(command));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}

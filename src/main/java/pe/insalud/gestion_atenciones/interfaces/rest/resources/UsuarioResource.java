package pe.insalud.gestion_atenciones.interfaces.rest.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.insalud.gestion_atenciones.application.internal.queryservices.AtencionQueryService;
import pe.insalud.gestion_atenciones.interfaces.rest.transform.MedicoDTO;
import pe.insalud.gestion_atenciones.interfaces.rest.transform.PacienteDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioResource {
    private final AtencionQueryService queryService;

    @GetMapping("/pacientes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PacienteDTO>> getPacientes() {
        return ResponseEntity.ok(queryService.getAllPacientes());
    }

    @GetMapping("/medicos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MedicoDTO>> getMedicos() {
        return ResponseEntity.ok(queryService.getAllMedicos());
    }
}

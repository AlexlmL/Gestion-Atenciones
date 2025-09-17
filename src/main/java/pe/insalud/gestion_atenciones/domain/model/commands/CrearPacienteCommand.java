package pe.insalud.gestion_atenciones.domain.model.commands;

import pe.insalud.gestion_atenciones.domain.model.valueobjects.Email;

public record CrearPacienteCommand(String nombre, Email email, String contraseña) {}

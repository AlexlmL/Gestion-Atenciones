package pe.insalud.gestion_atenciones.domain.model.commands;

import pe.insalud.gestion_atenciones.domain.model.valueobjects.Email;

/**
 * Comando para crear un nuevo paciente en el sistema
 * Contiene los datos necesarios para registrar al paciente

 * @param nombre Nombre completo del paciente
 * @param email Email del paciente, representado como Value Object Email
 * @param contrasena Contraseña del paciente, que será encriptada antes de almacenarse
 */
public record CrearPacienteCommand(String nombre, Email email, String contrasena) {}

package pe.insalud.gestion_atenciones.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador global de excepciones para la API REST
 * Captura diferentes tipos de excepciones y devuelve respuestas
 * HTTP consistentes con un mensaje de error.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja todas las excepciones de tipo RuntimeException
     * Se devuelve un estado HTTP 400 (BAD_REQUEST) con un mensaje
     * @param ex excepción lanzada
     * @return ResponseEntity con el error
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja IllegalArgumentException, útil para validaciones simples
     * como ids nulos o parámetros inválidos
     * @param ex excepción lanzada
     * @return ResponseEntity con el error
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja cualquier otra excepción no prevista.
     * Devuelve un estado HTTP 500 (INTERNAL_SERVER_ERROR)
     * con un mensaje genérico
     * @param ex excepción lanzada
     * @return ResponseEntity con el error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Ocurrió un error inesperado");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

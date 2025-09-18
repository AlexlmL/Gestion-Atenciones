package pe.insalud.gestion_atenciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Clase principal de la aplicación Spring Boot
 * Inicia la aplicación y habilita la auditoría JPA
 */
@SpringBootApplication
@EnableJpaAuditing
public class GestionAtencionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionAtencionesApplication.class, args);
	}

}

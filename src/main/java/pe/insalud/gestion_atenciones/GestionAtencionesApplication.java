package pe.insalud.gestion_atenciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestionAtencionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionAtencionesApplication.class, args);
	}

}

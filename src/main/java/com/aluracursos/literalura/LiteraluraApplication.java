package com.aluracursos.literalura;

import com.aluracursos.literalura.principal.AppConsola;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	private final AppConsola appConsola;

	public LiteraluraApplication(AppConsola appConsola) {
		this.appConsola = appConsola;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		appConsola.iniciarApp();
	}
}

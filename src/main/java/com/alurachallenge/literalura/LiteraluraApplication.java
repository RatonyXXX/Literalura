package com.alurachallenge.literalura;

import com.alurachallenge.literalura.Principal.Principal;
import com.alurachallenge.literalura.repository.AutoresRepository;
import com.alurachallenge.literalura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibrosRepository librosRepositorio;
	@Autowired
	private AutoresRepository autoresRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(librosRepositorio, autoresRepositorio);
		principal.mostrarMenu();
	}
}
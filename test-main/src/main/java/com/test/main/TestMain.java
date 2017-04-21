/*
 * Product: OMotor
 * Copyright (C) 2017 OMotor. All Rights Reserved.
 */
package com.test.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.test.model.entities.Usuario;
import com.test.model.entities.UsuarioRepository;
import com.test.model.pojo.UsuarioTO;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;	

/**
 * Start da aplicação
 * @author esdrastavares
 *
 */
@SpringBootApplication
@ComponentScan({
	"com.test.api"
})
public class TestMain {

	private static final Logger log = LoggerFactory.getLogger(TestMain.class);

	public static void main(String[] args) {
		SpringApplication.run(TestMain.class, args);
	}
	
	@Bean
	public CommandLineRunner runApp(UsuarioRepository repository) {
		
		return (args) -> {
			repository.save(new Usuario(new UsuarioTO("Nome 1", "Email 1", "Telefone 1", "Sexo 1")));
			repository.save(new Usuario(new UsuarioTO("Nome 2", "Email 2", "Telefone 2", "Sexo 2")));
			repository.save(new Usuario(new UsuarioTO("Nome 3", "Email 3", "Telefone 3", "Sexo 3")));


			log.info("Usuários encontrados com findAll():");
			log.info("-------------------------------");
			for (Usuario usuario : repository.findAll()) {
				log.info(usuario.toString());
			}
			log.info("");


		    Optional<Usuario> usuario = repository.findOne(1L);
			log.info("Usuário encontrado com findOne(1L):");
			log.info("--------------------------------");
			
			if(usuario.isPresent()) {
				log.info(usuario.get().toString());	
			} else {
				log.info("Usuário não encontrado com findOne(1L):");
				
			}
			
			log.info("");
		};
	}	
}

package com.duoc.msclientes;

import com.duoc.msclientes.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsclientesApplication {

	public static void main(String[] args) {

		SpringApplication.run(MsclientesApplication.class, args);
	}


	@Bean
	public CommandLineRunner iniData(ClienteRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				// creo y guardo los 3 objetos clientes manualmente
				System.out.println("Base de datos poblada con 3 registros.");
			}
		};
	}
}
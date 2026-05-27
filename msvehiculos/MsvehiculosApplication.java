package com.duoc.msvehiculos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class MsvehiculosApplication {

	// esta línea esté exactamente así
	private static final Logger logger = LoggerFactory.getLogger(MsvehiculosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MsvehiculosApplication.class, args);


		logger.info("   MS-VEHICULOS INICIADO EN PUERTO 8082   ");

	}
}
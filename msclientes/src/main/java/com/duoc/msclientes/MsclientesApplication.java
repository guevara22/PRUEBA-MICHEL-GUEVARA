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
}

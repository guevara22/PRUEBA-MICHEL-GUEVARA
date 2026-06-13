package com.duoc.mspagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MspagosApplication {
    public static void main(String[] args) {
        SpringApplication.run(MspagosApplication.class, args);
    }
}

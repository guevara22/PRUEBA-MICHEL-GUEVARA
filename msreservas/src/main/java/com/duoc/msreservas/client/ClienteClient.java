package com.duoc.msreservas.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "CLIENTES-SERVICE")
public interface ClienteClient {
    @GetMapping("/api/v1/clientes/{id}") ClienteRemotoDTO findById(@PathVariable Integer id);
}

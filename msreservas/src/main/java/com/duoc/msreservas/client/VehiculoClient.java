package com.duoc.msreservas.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "vehiculos", url = "${vehiculos.url}")
public interface VehiculoClient {
    @GetMapping("/api/v1/vehiculos/{id}") VehiculoRemotoDTO findById(@PathVariable Integer id);
}

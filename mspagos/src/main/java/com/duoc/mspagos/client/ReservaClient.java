package com.duoc.mspagos.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "reservas", url = "${reservas.url}")
public interface ReservaClient {
    @GetMapping("/api/v1/reservas/{id}") ReservaRemotaDTO findById(@PathVariable Integer id);
}

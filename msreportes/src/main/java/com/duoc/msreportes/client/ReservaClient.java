package com.duoc.msreportes.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
@FeignClient(name = "reservas", url = "${reservas.url}")
public interface ReservaClient { @GetMapping("/api/v1/reservas") List<Object> findAll(); }

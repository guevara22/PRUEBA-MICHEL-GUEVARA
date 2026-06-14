package com.duoc.msreportes.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
@FeignClient(name = "PAGOS-SERVICE")
public interface PagoClient { @GetMapping("/api/v1/pagos") List<PagoRemotoDTO> findAll(); }

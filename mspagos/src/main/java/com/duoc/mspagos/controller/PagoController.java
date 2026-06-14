package com.duoc.mspagos.controller;

import com.duoc.mspagos.dto.PagoDTO;
import com.duoc.mspagos.dto.PagoRequestDTO;
import com.duoc.mspagos.service.PagoService;
import java.math.BigDecimal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService service;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PagoDTO> create(@Valid @RequestBody PagoRequestDTO dto) {
        PagoDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/pagos/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> update(@PathVariable Integer id, @Valid @RequestBody PagoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PagoDTO>> buscar(@RequestParam BigDecimal montoMin, @RequestParam BigDecimal montoMax) {
        return ResponseEntity.ok(service.buscarPorRango(montoMin, montoMax));
    }
}

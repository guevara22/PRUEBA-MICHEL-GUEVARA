package com.duoc.msreportes.controller;

import com.duoc.msreportes.dto.ReporteDTO;
import com.duoc.msreportes.dto.ReporteRequestDTO;
import com.duoc.msreportes.service.ReporteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
@RequiredArgsConstructor
public class ReporteController {
    private final ReporteService service;

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ReporteDTO> create(@Valid @RequestBody ReporteRequestDTO dto) {
        ReporteDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/reportes/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> update(@PathVariable Integer id, @Valid @RequestBody ReporteRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/consolidado")
    public ResponseEntity<ReporteDTO> consolidado() {
        return ResponseEntity.ok(service.generarConsolidado());
    }
}

package com.duoc.mssucursales.controller;

import com.duoc.mssucursales.dto.SucursalDTO;
import com.duoc.mssucursales.dto.SucursalRequestDTO;
import com.duoc.mssucursales.service.SucursalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
@RequiredArgsConstructor
public class SucursalController {
    private final SucursalService service;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> create(@Valid @RequestBody SucursalRequestDTO dto) {
        SucursalDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/sucursales/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> update(@PathVariable Integer id, @Valid @RequestBody SucursalRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/operativas")
    public ResponseEntity<List<SucursalDTO>> operativas() {
        return ResponseEntity.ok(service.buscarOperativas());
    }
}

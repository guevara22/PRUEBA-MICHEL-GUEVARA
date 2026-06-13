package com.duoc.mssucursales.controller;

import com.duoc.mssucursales.dto.RegionDTO;
import com.duoc.mssucursales.dto.RegionRequestDTO;
import com.duoc.mssucursales.service.RegionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/regiones")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService service;

    @GetMapping
    public ResponseEntity<List<RegionDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionRequestDTO dto) {
        RegionDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/regiones/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> update(@PathVariable Integer id, @Valid @RequestBody RegionRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

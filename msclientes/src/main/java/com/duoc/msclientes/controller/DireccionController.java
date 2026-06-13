package com.duoc.msclientes.controller;

import com.duoc.msclientes.dto.DireccionDTO;
import com.duoc.msclientes.dto.DireccionRequestDTO;
import com.duoc.msclientes.service.DireccionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/direcciones")
@RequiredArgsConstructor
public class DireccionController {
    private final DireccionService service;

    @GetMapping
    public ResponseEntity<List<DireccionDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<DireccionDTO> create(@Valid @RequestBody DireccionRequestDTO dto) {
        DireccionDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/direcciones/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionDTO> update(@PathVariable Integer id, @Valid @RequestBody DireccionRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

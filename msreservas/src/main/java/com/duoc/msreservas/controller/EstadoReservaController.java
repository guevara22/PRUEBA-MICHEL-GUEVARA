package com.duoc.msreservas.controller;

import com.duoc.msreservas.dto.EstadoReservaDTO;
import com.duoc.msreservas.dto.EstadoReservaRequestDTO;
import com.duoc.msreservas.service.EstadoReservaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estados-reserva")
@RequiredArgsConstructor
public class EstadoReservaController {
    private final EstadoReservaService service;

    @GetMapping
    public ResponseEntity<List<EstadoReservaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoReservaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EstadoReservaDTO> create(@Valid @RequestBody EstadoReservaRequestDTO dto) {
        EstadoReservaDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/estados-reserva/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoReservaDTO> update(@PathVariable Integer id, @Valid @RequestBody EstadoReservaRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

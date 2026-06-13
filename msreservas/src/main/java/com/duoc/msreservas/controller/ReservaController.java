package com.duoc.msreservas.controller;

import com.duoc.msreservas.dto.ReservaDTO;
import com.duoc.msreservas.dto.ReservaRequestDTO;
import com.duoc.msreservas.service.ReservaService;

import com.duoc.msreservas.mapper.ReservaMapper;
import com.duoc.msreservas.repository.ReservaRepository;

import java.time.LocalDate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService service;
    private final ReservaRepository repository;
    private final ReservaMapper mapper;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> create(@Valid @RequestBody ReservaRequestDTO dto) {
        ReservaDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/reservas/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> update(@PathVariable Integer id, @Valid @RequestBody ReservaRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ReservaDTO>> buscar(@RequestParam LocalDate fechaDesde) {
        return ResponseEntity.ok(repository.buscarDesdeFecha(fechaDesde).stream().map(mapper::toDTO).toList());
    }
}

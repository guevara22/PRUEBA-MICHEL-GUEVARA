package com.duoc.msempleados.controller;

import com.duoc.msempleados.dto.EmpleadoDTO;
import com.duoc.msempleados.dto.EmpleadoRequestDTO;
import com.duoc.msempleados.service.EmpleadoService;

import com.duoc.msempleados.mapper.EmpleadoMapper;
import com.duoc.msempleados.repository.EmpleadoRepository;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoService service;
    private final EmpleadoRepository repository;
    private final EmpleadoMapper mapper;

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> create(@Valid @RequestBody EmpleadoRequestDTO dto) {
        EmpleadoDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/empleados/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> update(@PathVariable Integer id, @Valid @RequestBody EmpleadoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activos")
    public ResponseEntity<List<EmpleadoDTO>> activosPorAnio(@RequestParam int anio) {
        return ResponseEntity.ok(repository.buscarActivosPorAnio(anio).stream().map(mapper::toDTO).toList());
    }
}

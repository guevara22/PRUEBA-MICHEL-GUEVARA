package com.duoc.msvehiculos.controller;

import com.duoc.msvehiculos.dto.VehiculoDTO;
import com.duoc.msvehiculos.dto.VehiculoRequestDTO;
import com.duoc.msvehiculos.service.VehiculoService;

import com.duoc.msvehiculos.mapper.VehiculoMapper;
import com.duoc.msvehiculos.repository.VehiculoRepository;
import java.math.BigDecimal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {
    private final VehiculoService service;
    private final VehiculoRepository repository;
    private final VehiculoMapper mapper;

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<VehiculoDTO> create(@Valid @RequestBody VehiculoRequestDTO dto) {
        VehiculoDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/vehiculos/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDTO> update(@PathVariable Integer id, @Valid @RequestBody VehiculoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<VehiculoDTO>> buscar(@RequestParam BigDecimal precioMaximo) {
        return ResponseEntity.ok(repository.findByDisponibleTrueAndPrecioArriendoDiarioLessThan(precioMaximo).stream().map(mapper::toDTO).toList());
    }
}

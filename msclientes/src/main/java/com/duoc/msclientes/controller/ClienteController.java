package com.duoc.msclientes.controller;

import com.duoc.msclientes.dto.ClienteDTO;
import com.duoc.msclientes.dto.ClienteRequestDTO;
import com.duoc.msclientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteRequestDTO dto) {
        ClienteDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/clientes/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteDTO>> buscar(@RequestParam String email) {
        return ResponseEntity.ok(service.buscarPorEmail(email));
    }
}

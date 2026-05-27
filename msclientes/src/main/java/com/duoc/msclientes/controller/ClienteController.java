package com.duoc.msclientes.controller;


import com.duoc.msclientes.mapper.ClienteMapper;
import com.duoc.msclientes.dto.ClienteRequestDTO;
import com.duoc.msclientes.model.Cliente;
import com.duoc.msclientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteMapper mapper;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(service.obtenerTodos());
    }


    @PostMapping
    public ResponseEntity<Cliente>  crear(@Valid @RequestBody ClienteRequestDTO dto){
    Cliente entidad = mapper.toEntity(dto);
        return new ResponseEntity<>(service.guardar(entidad), HttpStatus.CREATED);
    }


}

package com.duoc.msvehiculos.controller;


import com.duoc.msvehiculos.modelo.Vehiculo;
import com.duoc.msvehiculos.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService service;

    @GetMapping
    public List<Vehiculo> getAll(){
        return service.listarTodo();
    }

    @PostMapping
    public ResponseEntity<Vehiculo> create(@Valid @RequestBody Vehiculo vehiculo ){
        return new ResponseEntity<>(service.guardar(vehiculo), HttpStatus.CREATED);
        
    }

}

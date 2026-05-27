package com.duoc.mspagos.controller;


import com.duoc.mspagos.entity.Pago;
import com.duoc.mspagos.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoRepository repository;

    @GetMapping
    public List<Pago> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pago> registrarpago(@RequestBody Pago pago) {
        Pago nuevoPago = repository.save(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

}

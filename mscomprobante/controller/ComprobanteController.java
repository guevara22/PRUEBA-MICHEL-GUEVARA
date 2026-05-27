package com.duoc.mscomprobante.controller;


import com.duoc.mscomprobante.entity.Comprobante;
import com.duoc.mscomprobante.repository.ComprobanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comprobantes")
public class ComprobanteController {
    @Autowired
    private ComprobanteRepository repository;

    @GetMapping
    public List<Comprobante> listarComprobantes(){
        return  repository.findAll();
    }

    @PostMapping
    public Comprobante crearComprobante(@RequestBody Comprobante comprobante){
        System.out.println("Generando nuevo comprobante para el pago");
        return repository.save(comprobante);
    }
}

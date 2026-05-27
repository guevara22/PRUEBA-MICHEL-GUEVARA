package com.duoc.msreservas.controller;


import com.duoc.msreservas.model.Reserva;
import com.duoc.msreservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository repository;

    @GetMapping
    public List<Reserva> listarTodas(){
        return repository.findAll();

    }
    @PostMapping
    public Reserva crear(@RequestBody Reserva reserva){
        return repository.save(reserva);
    }
}

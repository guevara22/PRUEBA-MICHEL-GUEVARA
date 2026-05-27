package com.duoc.mssucursales.controller;



import com.duoc.mssucursales.entity.Sucursal;
import com.duoc.mssucursales.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalRepository repository;

    @GetMapping
    public List<Sucursal> listarSucursales(){
        return repository.findAll();

    }

    @PostMapping
    public Sucursal guardarSucursal(@RequestBody Sucursal sucursal) {
        return repository.save(sucursal);
    }
}

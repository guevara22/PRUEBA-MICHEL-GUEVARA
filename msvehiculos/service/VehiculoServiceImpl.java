package com.duoc.msvehiculos.service;


import com.duoc.msvehiculos.modelo.Vehiculo;
import com.duoc.msvehiculos.repository.VehiculoRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VehiculoServiceImpl implements VehiculoService {

    private static final Logger log = LoggerFactory.getLogger(VehiculoServiceImpl.class);


    @Autowired
    private VehiculoRepository repository;

    @Override
    public List<Vehiculo> listarTodo(){
        log.info("Consultando todos los vehiculos");
        return  repository.findAll();

    }
    @Override
    public  Vehiculo guardar(Vehiculo vehiculo){
        log.info("Guardando Vehiculo: {} {}", vehiculo.getMarca(), vehiculo.getModelo());
        return repository.save(vehiculo);
    }


}

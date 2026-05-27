package com.duoc.msclientes.service;


import com.duoc.msclientes.model.Cliente;
import com.duoc.msclientes.repository.ClienteRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ClienteService {
    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> obtenerTodos(){
        log.info("Buscando todos los clientes en la base de datos");
        return repo.findAll();
    }

    public Cliente guardar(Cliente cliente){
        try {
            log.info("Intentando guardar cliente: {}", cliente.getNombre());
            return  repo.save(cliente);
        }   catch (Exception e) {
            log.error("Error al guardar:{}",e.getMessage());
            throw e;
        }
    }

}

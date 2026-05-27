package com.duoc.msclientes.mapper;


import com.duoc.msclientes.dto.ClienteRequestDTO;
import com.duoc.msclientes.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {


    public Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setEdad(dto.getEdad());
        cliente.setEsVip(dto.getEsVip());
        cliente.setFechaAlta(dto.getFechaAlta());
        return cliente;

    }
}




package com.duoc.msclientes.mapper;

import com.duoc.msclientes.dto.ClienteDTO;
import com.duoc.msclientes.dto.ClienteRequestDTO;
import com.duoc.msclientes.model.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toEntity(ClienteRequestDTO dto) {
        Cliente entity = new Cliente();
        update(entity, dto);
        return entity;
    }

    public void update(Cliente entity, ClienteRequestDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setEmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
        entity.setEdad(dto.getEdad());
        entity.setEsVip(dto.isEsVip());
        entity.setFechaAlta(dto.getFechaAlta());
    }

    public ClienteDTO toDTO(Cliente entity) {
        return ClienteDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .edad(entity.getEdad())
                .esVip(entity.isEsVip())
                .fechaAlta(entity.getFechaAlta())
                .build();
    }
}

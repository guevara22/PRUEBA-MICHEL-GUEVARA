package com.duoc.msclientes.mapper;

import com.duoc.msclientes.dto.DireccionDTO;
import com.duoc.msclientes.dto.DireccionRequestDTO;
import com.duoc.msclientes.model.Direccion;
import com.duoc.msclientes.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class DireccionMapper {
    public Direccion toEntity(DireccionRequestDTO dto, Cliente cliente) {
        Direccion entity = new Direccion();
        update(entity, dto, cliente);
        return entity;
    }

    public void update(Direccion entity, DireccionRequestDTO dto, Cliente cliente) {
        entity.setCalle(dto.getCalle());
        entity.setComuna(dto.getComuna());
        entity.setCiudad(dto.getCiudad());
        entity.setCodigoPostal(dto.getCodigoPostal());
        entity.setNumero(dto.getNumero());
        entity.setPrincipal(dto.isPrincipal());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setCliente(cliente);
    }

    public DireccionDTO toDTO(Direccion entity) {
        return DireccionDTO.builder()
                .id(entity.getId())
                .calle(entity.getCalle())
                .comuna(entity.getComuna())
                .ciudad(entity.getCiudad())
                .codigoPostal(entity.getCodigoPostal())
                .numero(entity.getNumero())
                .principal(entity.isPrincipal())
                .fechaRegistro(entity.getFechaRegistro())
                .clienteId(entity.getCliente().getId())
                .clienteNombre(entity.getCliente().getNombre())
                .build();
    }
}

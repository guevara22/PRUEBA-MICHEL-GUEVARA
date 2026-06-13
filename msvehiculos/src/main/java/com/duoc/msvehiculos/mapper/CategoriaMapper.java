package com.duoc.msvehiculos.mapper;

import com.duoc.msvehiculos.dto.CategoriaDTO;
import com.duoc.msvehiculos.dto.CategoriaRequestDTO;
import com.duoc.msvehiculos.model.Categoria;

import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public Categoria toEntity(CategoriaRequestDTO dto) {
        Categoria entity = new Categoria();
        update(entity, dto);
        return entity;
    }

    public void update(Categoria entity, CategoriaRequestDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setTarifaBase(dto.getTarifaBase());
        entity.setCapacidadPasajeros(dto.getCapacidadPasajeros());
        entity.setActiva(dto.isActiva());
        entity.setFechaCreacion(dto.getFechaCreacion());
    }

    public CategoriaDTO toDTO(Categoria entity) {
        return CategoriaDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .tarifaBase(entity.getTarifaBase())
                .capacidadPasajeros(entity.getCapacidadPasajeros())
                .activa(entity.isActiva())
                .fechaCreacion(entity.getFechaCreacion())
                .build();
    }
}

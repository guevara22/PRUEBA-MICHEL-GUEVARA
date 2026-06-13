package com.duoc.mssucursales.mapper;

import com.duoc.mssucursales.dto.RegionDTO;
import com.duoc.mssucursales.dto.RegionRequestDTO;
import com.duoc.mssucursales.model.Region;

import org.springframework.stereotype.Component;

@Component
public class RegionMapper {
    public Region toEntity(RegionRequestDTO dto) {
        Region entity = new Region();
        update(entity, dto);
        return entity;
    }

    public void update(Region entity, RegionRequestDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setCodigo(dto.getCodigo());
        entity.setDescripcion(dto.getDescripcion());
        entity.setNumeroProvincias(dto.getNumeroProvincias());
        entity.setActiva(dto.isActiva());
        entity.setFechaCreacion(dto.getFechaCreacion());
    }

    public RegionDTO toDTO(Region entity) {
        return RegionDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .codigo(entity.getCodigo())
                .descripcion(entity.getDescripcion())
                .numeroProvincias(entity.getNumeroProvincias())
                .activa(entity.isActiva())
                .fechaCreacion(entity.getFechaCreacion())
                .build();
    }
}

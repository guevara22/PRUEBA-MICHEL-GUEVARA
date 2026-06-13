package com.duoc.mssucursales.mapper;

import com.duoc.mssucursales.dto.SucursalDTO;
import com.duoc.mssucursales.dto.SucursalRequestDTO;
import com.duoc.mssucursales.model.Sucursal;
import com.duoc.mssucursales.model.Region;
import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {
    public Sucursal toEntity(SucursalRequestDTO dto, Region region) {
        Sucursal entity = new Sucursal();
        update(entity, dto, region);
        return entity;
    }

    public void update(Sucursal entity, SucursalRequestDTO dto, Region region) {
        entity.setNombre(dto.getNombre());
        entity.setDireccion(dto.getDireccion());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        entity.setCapacidadVehiculos(dto.getCapacidadVehiculos());
        entity.setOperativa(dto.isOperativa());
        entity.setFechaApertura(dto.getFechaApertura());
        entity.setRegion(region);
    }

    public SucursalDTO toDTO(Sucursal entity) {
        return SucursalDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .capacidadVehiculos(entity.getCapacidadVehiculos())
                .operativa(entity.isOperativa())
                .fechaApertura(entity.getFechaApertura())
                .regionId(entity.getRegion().getId())
                .regionNombre(entity.getRegion().getNombre())
                .build();
    }
}

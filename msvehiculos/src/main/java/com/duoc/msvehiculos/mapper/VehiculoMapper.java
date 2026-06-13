package com.duoc.msvehiculos.mapper;

import com.duoc.msvehiculos.dto.VehiculoDTO;
import com.duoc.msvehiculos.dto.VehiculoRequestDTO;
import com.duoc.msvehiculos.model.Vehiculo;
import com.duoc.msvehiculos.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class VehiculoMapper {
    public Vehiculo toEntity(VehiculoRequestDTO dto, Categoria categoria) {
        Vehiculo entity = new Vehiculo();
        update(entity, dto, categoria);
        return entity;
    }

    public void update(Vehiculo entity, VehiculoRequestDTO dto, Categoria categoria) {
        entity.setPatente(dto.getPatente());
        entity.setMarca(dto.getMarca());
        entity.setModelo(dto.getModelo());
        entity.setAnio(dto.getAnio());
        entity.setPrecioArriendoDiario(dto.getPrecioArriendoDiario());
        entity.setDisponible(dto.isDisponible());
        entity.setFechaIngreso(dto.getFechaIngreso());
        entity.setCategoria(categoria);
    }

    public VehiculoDTO toDTO(Vehiculo entity) {
        return VehiculoDTO.builder()
                .id(entity.getId())
                .patente(entity.getPatente())
                .marca(entity.getMarca())
                .modelo(entity.getModelo())
                .anio(entity.getAnio())
                .precioArriendoDiario(entity.getPrecioArriendoDiario())
                .disponible(entity.isDisponible())
                .fechaIngreso(entity.getFechaIngreso())
                .categoriaId(entity.getCategoria().getId())
                .categoriaNombre(entity.getCategoria().getNombre())
                .build();
    }
}

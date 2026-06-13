package com.duoc.msreservas.mapper;

import com.duoc.msreservas.dto.EstadoReservaDTO;
import com.duoc.msreservas.dto.EstadoReservaRequestDTO;
import com.duoc.msreservas.model.EstadoReserva;

import org.springframework.stereotype.Component;

@Component
public class EstadoReservaMapper {
    public EstadoReserva toEntity(EstadoReservaRequestDTO dto) {
        EstadoReserva entity = new EstadoReserva();
        update(entity, dto);
        return entity;
    }

    public void update(EstadoReserva entity, EstadoReservaRequestDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrioridad(dto.getPrioridad());
        entity.setPermiteModificacion(dto.isPermiteModificacion());
        entity.setActivo(dto.isActivo());
        entity.setFechaCreacion(dto.getFechaCreacion());
    }

    public EstadoReservaDTO toDTO(EstadoReserva entity) {
        return EstadoReservaDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .prioridad(entity.getPrioridad())
                .permiteModificacion(entity.isPermiteModificacion())
                .activo(entity.isActivo())
                .fechaCreacion(entity.getFechaCreacion())
                .build();
    }
}

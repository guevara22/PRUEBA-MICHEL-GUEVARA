package com.duoc.msempleados.mapper;

import com.duoc.msempleados.dto.EmpleadoDTO;
import com.duoc.msempleados.dto.EmpleadoRequestDTO;
import com.duoc.msempleados.model.Empleado;

import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {
    public Empleado toEntity(EmpleadoRequestDTO dto) {
        Empleado entity = new Empleado();
        update(entity, dto);
        return entity;
    }

    public void update(Empleado entity, EmpleadoRequestDTO dto) {
        entity.setRut(dto.getRut());
        entity.setNombreCompleto(dto.getNombreCompleto());
        entity.setEmail(dto.getEmail());
        entity.setCargo(dto.getCargo());
        entity.setSueldo(dto.getSueldo());
        entity.setActivo(dto.isActivo());
        entity.setFechaContratacion(dto.getFechaContratacion());
        entity.setSucursalId(dto.getSucursalId());
    }

    public EmpleadoDTO toDTO(Empleado entity) {
        return EmpleadoDTO.builder()
                .id(entity.getId())
                .rut(entity.getRut())
                .nombreCompleto(entity.getNombreCompleto())
                .email(entity.getEmail())
                .cargo(entity.getCargo())
                .sueldo(entity.getSueldo())
                .activo(entity.isActivo())
                .fechaContratacion(entity.getFechaContratacion())
                .sucursalId(entity.getSucursalId())
                .build();
    }
}

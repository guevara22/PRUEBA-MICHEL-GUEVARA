package com.duoc.msreportes.mapper;

import com.duoc.msreportes.dto.ReporteDTO;
import com.duoc.msreportes.dto.ReporteRequestDTO;
import com.duoc.msreportes.model.Reporte;

import org.springframework.stereotype.Component;

@Component
public class ReporteMapper {
    public Reporte toEntity(ReporteRequestDTO dto) {
        Reporte entity = new Reporte();
        update(entity, dto);
        return entity;
    }

    public void update(Reporte entity, ReporteRequestDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setTipo(dto.getTipo());
        entity.setDescripcion(dto.getDescripcion());
        entity.setTotalReservas(dto.getTotalReservas());
        entity.setMontoTotalPagos(dto.getMontoTotalPagos());
        entity.setGenerado(dto.isGenerado());
        entity.setFechaGeneracion(dto.getFechaGeneracion());
    }

    public ReporteDTO toDTO(Reporte entity) {
        return ReporteDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .tipo(entity.getTipo())
                .descripcion(entity.getDescripcion())
                .totalReservas(entity.getTotalReservas())
                .montoTotalPagos(entity.getMontoTotalPagos())
                .generado(entity.isGenerado())
                .fechaGeneracion(entity.getFechaGeneracion())
                .build();
    }
}

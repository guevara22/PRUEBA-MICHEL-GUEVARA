package com.duoc.msreservas.mapper;

import com.duoc.msreservas.dto.ReservaDTO;
import com.duoc.msreservas.dto.ReservaRequestDTO;
import com.duoc.msreservas.model.Reserva;
import com.duoc.msreservas.model.EstadoReserva;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {
    public Reserva toEntity(ReservaRequestDTO dto, EstadoReserva estadoReserva) {
        Reserva entity = new Reserva();
        update(entity, dto, estadoReserva);
        return entity;
    }

    public void update(Reserva entity, ReservaRequestDTO dto, EstadoReserva estadoReserva) {
        entity.setClienteId(dto.getClienteId());
        entity.setVehiculoId(dto.getVehiculoId());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setMontoTotal(dto.getMontoTotal());
        entity.setObservaciones(dto.getObservaciones());
        entity.setConfirmada(dto.isConfirmada());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setEstadoReserva(estadoReserva);
    }

    public ReservaDTO toDTO(Reserva entity) {
        return ReservaDTO.builder()
                .id(entity.getId())
                .clienteId(entity.getClienteId())
                .vehiculoId(entity.getVehiculoId())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .montoTotal(entity.getMontoTotal())
                .observaciones(entity.getObservaciones())
                .confirmada(entity.isConfirmada())
                .fechaCreacion(entity.getFechaCreacion())
                .estadoReservaId(entity.getEstadoReserva().getId())
                .estadoReservaNombre(entity.getEstadoReserva().getNombre())
                .build();
    }
}

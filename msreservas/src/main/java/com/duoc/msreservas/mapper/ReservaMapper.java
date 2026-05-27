package com.duoc.msreservas.mapper;


import com.duoc.msreservas.model.Reserva;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {
    public Reserva toEntity(ReservaRequestDTO dto) {
        Reserva reserva = new Reserva();
        reserva.setClienteId(dto.getClienteId());
        reserva.setVehiculoId(dto.getVehiculoId());
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFin(dto.getFechaFin());
        reserva.setMontoTotal(dto.getMontoTotal());
        reserva.setEstado("PENDIENTE");
        return reserva;
    }
}


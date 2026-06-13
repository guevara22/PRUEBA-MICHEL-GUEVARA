package com.duoc.msreservas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {
    private Integer id;
    private Integer clienteId;
    private Integer vehiculoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal montoTotal;
    private String observaciones;
    private boolean confirmada;
    private LocalDateTime fechaCreacion;

    private Integer estadoReservaId;
    private String estadoReservaNombre;

}

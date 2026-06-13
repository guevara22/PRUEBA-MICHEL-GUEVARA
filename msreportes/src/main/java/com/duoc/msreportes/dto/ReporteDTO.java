package com.duoc.msreportes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {
    private Integer id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private Integer totalReservas;
    private BigDecimal montoTotalPagos;
    private boolean generado;
    private LocalDateTime fechaGeneracion;

}

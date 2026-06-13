package com.duoc.msvehiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {
    private Integer id;
    private String patente;
    private String marca;
    private String modelo;
    private Integer anio;
    private BigDecimal precioArriendoDiario;
    private boolean disponible;
    private LocalDate fechaIngreso;

    private Integer categoriaId;
    private String categoriaNombre;

}

package com.duoc.msempleados.dto;

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
public class EmpleadoDTO {
    private Integer id;
    private String rut;
    private String nombreCompleto;
    private String email;
    private String cargo;
    private BigDecimal sueldo;
    private boolean activo;
    private LocalDate fechaContratacion;
    private Integer sucursalId;

}

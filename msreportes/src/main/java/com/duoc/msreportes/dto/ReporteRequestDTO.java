package com.duoc.msreportes.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ReporteRequestDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    private String nombre;

    @NotBlank
    @Size(min = 3, max = 40)
    private String tipo;

    @NotBlank
    @Size(min = 5, max = 250)
    private String descripcion;

    @NotNull
    @Min(0)
    private Integer totalReservas;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 12, fraction = 2)
    private BigDecimal montoTotalPagos;

    private boolean generado;

    @NotNull
    @PastOrPresent
    private LocalDateTime fechaGeneracion;

}

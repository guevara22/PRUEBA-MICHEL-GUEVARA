package com.duoc.msreservas.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ReservaRequestDTO {
    @NotNull
    @Positive
    private Integer clienteId;

    @NotNull
    @Positive
    private Integer vehiculoId;

    @NotNull
    @FutureOrPresent
    private LocalDate fechaInicio;

    @NotNull
    @Future
    private LocalDate fechaFin;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal montoTotal;

    @NotBlank
    @Size(min = 3, max = 250)
    private String observaciones;

    private boolean confirmada;

    @NotNull
    @PastOrPresent
    private LocalDateTime fechaCreacion;

    @NotNull
    @Positive
    private Integer estadoReservaId;

}

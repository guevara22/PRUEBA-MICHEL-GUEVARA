package com.duoc.msreservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "1")
    private Integer clienteId;

    @NotNull
    @Positive
    @Schema(example = "1")
    private Integer vehiculoId;

    @NotNull
    @FutureOrPresent
    @Schema(example = "2026-06-20")
    private LocalDate fechaInicio;

    @NotNull
    @Future
    @Schema(example = "2026-06-25")
    private LocalDate fechaFin;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    @Schema(example = "180000")
    private BigDecimal montoTotal;

    @NotBlank
    @Size(min = 3, max = 250)
    @Schema(example = "Retiro en sucursal central")
    private String observaciones;

    @Schema(example = "true")
    private boolean confirmada;

    @NotNull
    @PastOrPresent
    @Schema(example = "2026-06-13T16:00:00")
    private LocalDateTime fechaCreacion;

    @NotNull
    @Positive
    @Schema(example = "2")
    private Integer estadoReservaId;

}

package com.duoc.msvehiculos.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class VehiculoRequestDTO {
    @NotBlank
    @Size(min = 5, max = 10)
    private String patente;

    @NotBlank
    @Size(min = 2, max = 50)
    private String marca;

    @NotBlank
    @Size(min = 1, max = 50)
    private String modelo;

    @NotNull
    @Min(1980)
    private Integer anio;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal precioArriendoDiario;

    private boolean disponible;

    @NotNull
    @PastOrPresent
    private LocalDate fechaIngreso;

    @NotNull
    @Positive
    private Integer categoriaId;

}

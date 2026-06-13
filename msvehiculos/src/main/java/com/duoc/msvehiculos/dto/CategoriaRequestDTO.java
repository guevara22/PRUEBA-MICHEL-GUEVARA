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
public class CategoriaRequestDTO {
    @NotBlank
    @Size(min = 2, max = 60)
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 200)
    private String descripcion;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal tarifaBase;

    @NotNull
    @Positive
    private Integer capacidadPasajeros;

    private boolean activa;

    @NotNull
    @PastOrPresent
    private LocalDate fechaCreacion;

}

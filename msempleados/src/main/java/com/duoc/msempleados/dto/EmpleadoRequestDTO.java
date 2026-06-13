package com.duoc.msempleados.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class EmpleadoRequestDTO {
    @NotBlank
    @Size(min = 8, max = 12)
    private String rut;

    @NotBlank
    @Size(min = 3, max = 120)
    private String nombreCompleto;

    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @NotBlank
    @Size(min = 3, max = 60)
    private String cargo;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal sueldo;

    private boolean activo;

    @NotNull
    @PastOrPresent
    private LocalDate fechaContratacion;

    @NotNull
    @Positive
    private Integer sucursalId;

}

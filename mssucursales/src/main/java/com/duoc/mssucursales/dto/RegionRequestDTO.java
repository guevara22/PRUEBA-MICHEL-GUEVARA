package com.duoc.mssucursales.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RegionRequestDTO {
    @NotBlank
    @Size(min = 2, max = 80)
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 10)
    private String codigo;

    @NotBlank
    @Size(min = 5, max = 180)
    private String descripcion;

    @NotNull
    @Positive
    private Integer numeroProvincias;

    private boolean activa;

    @NotNull
    @PastOrPresent
    private LocalDate fechaCreacion;

}

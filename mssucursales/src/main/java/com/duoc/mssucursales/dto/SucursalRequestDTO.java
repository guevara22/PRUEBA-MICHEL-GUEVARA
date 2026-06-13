package com.duoc.mssucursales.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SucursalRequestDTO {
    @NotBlank
    @Size(min = 2, max = 80)
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 150)
    private String direccion;

    @NotBlank
    @Size(min = 8, max = 20)
    private String telefono;

    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @NotNull
    @Positive
    private Integer capacidadVehiculos;

    private boolean operativa;

    @NotNull
    @PastOrPresent
    private LocalDate fechaApertura;

    @NotNull
    @Positive
    private Integer regionId;

}

package com.duoc.msreservas.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EstadoReservaRequestDTO {
    @NotBlank
    @Size(min = 2, max = 40)
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 150)
    private String descripcion;

    @NotNull
    @Positive
    private Integer prioridad;

    private boolean permiteModificacion;

    private boolean activo;

    @NotNull
    @PastOrPresent
    private LocalDate fechaCreacion;

}

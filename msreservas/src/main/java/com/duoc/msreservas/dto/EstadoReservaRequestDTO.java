package com.duoc.msreservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "CONFIRMADA")
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 150)
    @Schema(example = "Reserva confirmada y vigente")
    private String descripcion;

    @NotNull
    @Positive
    @Schema(example = "2")
    private Integer prioridad;

    @Schema(example = "true")
    private boolean permiteModificacion;

    @Schema(example = "true")
    private boolean activo;

    @NotNull
    @PastOrPresent
    @Schema(example = "2026-06-13")
    private LocalDate fechaCreacion;

}

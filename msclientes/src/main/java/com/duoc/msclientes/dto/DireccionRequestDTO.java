package com.duoc.msclientes.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DireccionRequestDTO {
    @NotBlank
    @Size(min = 2, max = 120)
    private String calle;

    @NotBlank
    @Size(min = 2, max = 80)
    private String comuna;

    @NotBlank
    @Size(min = 2, max = 80)
    private String ciudad;

    @NotBlank
    @Size(min = 3, max = 12)
    private String codigoPostal;

    @NotNull
    @Positive
    private Integer numero;

    private boolean principal;

    @NotNull
    @PastOrPresent
    private LocalDate fechaRegistro;

    @NotNull
    @Positive
    private Integer clienteId;

}

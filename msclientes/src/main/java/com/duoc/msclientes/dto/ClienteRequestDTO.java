package com.duoc.msclientes.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClienteRequestDTO {
    @NotBlank
    @Size(min = 2, max = 80)
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 80)
    private String apellido;

    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String telefono;

    @NotNull
    @Min(18)
    private Integer edad;

    private boolean esVip;

    @NotNull
    @PastOrPresent
    private LocalDate fechaAlta;

}

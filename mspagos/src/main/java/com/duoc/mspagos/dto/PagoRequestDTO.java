package com.duoc.mspagos.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PagoRequestDTO {
    @NotNull
    @Positive
    private Integer reservaId;

    @NotBlank
    @Size(min = 5, max = 60)
    private String codigoTransaccion;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal monto;

    @NotBlank
    @Size(min = 3, max = 30)
    private String medioPago;

    private boolean pagado;

    @NotNull
    @PastOrPresent
    private LocalDateTime fechaPago;

    @NotBlank
    @Size(min = 3, max = 100)
    private String referencia;

}

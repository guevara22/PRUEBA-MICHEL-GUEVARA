package com.duoc.mspagos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "1")
    private Integer reservaId;

    @NotBlank
    @Size(min = 5, max = 60)
    @Schema(example = "TRX-00004")
    private String codigoTransaccion;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    @Schema(example = "120000")
    private BigDecimal monto;

    @NotBlank
    @Size(min = 3, max = 30)
    @Schema(example = "CREDITO")
    private String medioPago;

    @Schema(example = "true")
    private boolean pagado;

    @NotNull
    @PastOrPresent
    @Schema(example = "2026-06-13T16:00:00")
    private LocalDateTime fechaPago;

    @NotBlank
    @Size(min = 3, max = 100)
    @Schema(example = "Pago completo reserva 1")
    private String referencia;

}

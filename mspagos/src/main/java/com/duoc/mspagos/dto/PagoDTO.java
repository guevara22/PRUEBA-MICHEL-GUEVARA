package com.duoc.mspagos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Integer id;
    private Integer reservaId;
    private String codigoTransaccion;
    private BigDecimal monto;
    private String medioPago;
    private boolean pagado;
    private LocalDateTime fechaPago;
    private String referencia;

}

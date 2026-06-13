package com.duoc.mspagos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer reservaId;

    @Column(nullable = false, unique = true)
    private String codigoTransaccion;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private String medioPago;

    @Column(nullable = false)
    private boolean pagado;

    @Column(nullable = false)
    private LocalDateTime fechaPago;

    @Column(nullable = false)
    private String referencia;

}

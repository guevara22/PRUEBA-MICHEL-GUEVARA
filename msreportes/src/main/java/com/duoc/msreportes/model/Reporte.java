package com.duoc.msreportes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "reportes")
@Getter
@Setter
@NoArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Integer totalReservas;

    @Column(nullable = false)
    private BigDecimal montoTotalPagos;

    @Column(nullable = false)
    private boolean generado;

    @Column(nullable = false)
    private LocalDateTime fechaGeneracion;

}

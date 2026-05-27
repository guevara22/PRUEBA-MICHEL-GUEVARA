package com.duoc.mscomprobante.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "comprobantes")
@Data
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pagoId;
    private String numeroBoleta;
    private LocalDateTime fechaEmision;

    @PrePersist
    protected void onCreate() {
        this.fechaEmision = LocalDateTime.now();
    }


}

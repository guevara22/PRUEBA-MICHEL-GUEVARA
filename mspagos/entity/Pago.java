package com.duoc.mspagos.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long reservaId;  //ID de q viene reservas
    private Double monto;
    private String medioPago;  // Si es "Debido" , "credito" o "transferencia"
    private LocalDateTime fechaTransaccion;
    
    @PrePersist
    protected void onCreate() {
        fechaTransaccion = LocalDateTime.now();
    }

}

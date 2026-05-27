package com.duoc.msreservas.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Reservas")
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rutCliente;
    private String patenteVehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;


}

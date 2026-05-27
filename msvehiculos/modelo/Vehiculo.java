package com.duoc.msvehiculos.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "vehiculos")
@Data
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "La patente no estar vacia")
    @Column(unique = true)
    private String patente;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @Min(value = 1980, message = "Año mo valido")
    private int anio;

    private boolean disponible;
    

}

package com.duoc.mssucursales.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sucursales")
@Data
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;      // LAS SUCURSALES SI SON EN LA ALAMEDA O UN AEROPUERTO
    private String Comuna;      // como dice comuna por ej "quinta normal" o "cerro navia"
    private String direccion;
    private String telefono;

}

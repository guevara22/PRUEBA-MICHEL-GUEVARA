package com.duoc.msclientes.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String email;
    private Integer edad;   // Numérico
    private  boolean esVip;
    private LocalDate fechaAlta;  //Fecha


    public Integer getId() { return id; }
    public void setId(Integer id ) {this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() {return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    public boolean isEsVip() { return esVip; }
    public void setEsVip(boolean esVip) { this.esVip = esVip; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }
}

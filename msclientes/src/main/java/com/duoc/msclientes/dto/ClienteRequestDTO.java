package com.duoc.msclientes.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClienteRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @Email(message = "Formato de email invalido")
    @NotBlank
    private String email;


    @Min(value = 18, message = "Debe ser mayor de edad")
    private Integer edad;

    @NotNull
    private boolean esVip;

    @PastOrPresent
    private LocalDate fechaAlta;


    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public boolean getEsVip() {
        return esVip;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }
}

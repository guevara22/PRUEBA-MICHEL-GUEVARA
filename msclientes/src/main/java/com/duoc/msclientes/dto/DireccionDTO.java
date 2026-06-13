package com.duoc.msclientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {
    private Integer id;
    private String calle;
    private String comuna;
    private String ciudad;
    private String codigoPostal;
    private Integer numero;
    private boolean principal;
    private LocalDate fechaRegistro;

    private Integer clienteId;
    private String clienteNombre;

}

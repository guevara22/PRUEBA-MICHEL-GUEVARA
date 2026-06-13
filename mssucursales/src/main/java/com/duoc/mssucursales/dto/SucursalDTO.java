package com.duoc.mssucursales.dto;

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
public class SucursalDTO {
    private Integer id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private Integer capacidadVehiculos;
    private boolean operativa;
    private LocalDate fechaApertura;

    private Integer regionId;
    private String regionNombre;

}

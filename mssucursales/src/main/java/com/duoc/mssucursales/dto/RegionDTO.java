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
public class RegionDTO {
    private Integer id;
    private String nombre;
    private String codigo;
    private String descripcion;
    private Integer numeroProvincias;
    private boolean activa;
    private LocalDate fechaCreacion;

}

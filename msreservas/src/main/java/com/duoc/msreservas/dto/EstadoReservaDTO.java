package com.duoc.msreservas.dto;

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
public class EstadoReservaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer prioridad;
    private boolean permiteModificacion;
    private boolean activo;
    private LocalDate fechaCreacion;

}

package com.duoc.mscategoria.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "categorias")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;  // SI SON "ECONOMICOS" O "LUJOS"
    private  Double precioDia;  //los precios base por dia
    private String descripcion;    // si los autos estan de bajo consumo


}

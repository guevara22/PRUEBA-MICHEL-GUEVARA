package com.duoc.msvehiculos.config;
import com.duoc.msvehiculos.model.*; import com.duoc.msvehiculos.repository.*;
import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import java.math.BigDecimal; import java.time.LocalDate;
@Configuration public class DataLoader {
 @Bean CommandLineRunner cargarVehiculos(CategoriaRepository categorias,VehiculoRepository vehiculos){return args->{if(categorias.count()==0){
  String[] nombres={"Economico","SUV","Premium"}; for(int i=0;i<3;i++){Categoria c=new Categoria();c.setNombre(nombres[i]);c.setDescripcion("Categoria de vehiculos "+nombres[i]);c.setTarifaBase(BigDecimal.valueOf(30000+i*20000));c.setCapacidadPasajeros(4+i);c.setActiva(true);c.setFechaCreacion(LocalDate.now());categorias.save(c);
  Vehiculo v=new Vehiculo();v.setPatente("ABCD"+(10+i));v.setMarca(new String[]{"Toyota","Hyundai","BMW"}[i]);v.setModelo(new String[]{"Yaris","Tucson","X3"}[i]);v.setAnio(2022+i);v.setPrecioArriendoDiario(BigDecimal.valueOf(40000+i*25000));v.setDisponible(true);v.setFechaIngreso(LocalDate.now());v.setCategoria(c);vehiculos.save(v);}}};}
}

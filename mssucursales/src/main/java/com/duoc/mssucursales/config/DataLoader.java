package com.duoc.mssucursales.config;
import com.duoc.mssucursales.model.*; import com.duoc.mssucursales.repository.*;
import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import java.time.LocalDate;
@Configuration public class DataLoader {
 @Bean CommandLineRunner cargarSucursales(RegionRepository regiones,SucursalRepository sucursales){return args->{if(regiones.count()==0){
  String[] nombres={"Metropolitana","Valparaiso","Biobio"};for(int i=0;i<3;i++){Region r=new Region();r.setNombre(nombres[i]);r.setCodigo("R"+(i+1));r.setDescripcion("Region "+nombres[i]+" de Chile");r.setNumeroProvincias(5+i);r.setActiva(true);r.setFechaCreacion(LocalDate.now());regiones.save(r);
  Sucursal s=new Sucursal();s.setNombre("Sucursal "+nombres[i]);s.setDireccion("Avenida Principal "+(100+i));s.setTelefono("22123456"+i);s.setEmail("sucursal"+i+"@arriendos.cl");s.setCapacidadVehiculos(30+i*10);s.setOperativa(true);s.setFechaApertura(LocalDate.now().minusYears(2));s.setRegion(r);sucursales.save(s);}}};}
}

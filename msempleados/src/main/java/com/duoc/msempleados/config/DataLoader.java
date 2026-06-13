package com.duoc.msempleados.config;
import com.duoc.msempleados.model.Empleado; import com.duoc.msempleados.repository.EmpleadoRepository;
import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import java.math.BigDecimal; import java.time.LocalDate;
@Configuration public class DataLoader {
 @Bean CommandLineRunner cargarEmpleados(EmpleadoRepository repo){return args->{if(repo.count()==0){String[] nombres={"Carla Diaz","Pedro Silva","Elena Mora"};for(int i=0;i<3;i++){Empleado e=new Empleado();e.setRut("1111111"+i+"-"+i);e.setNombreCompleto(nombres[i]);e.setEmail("empleado"+i+"@arriendos.cl");e.setCargo(new String[]{"Ejecutiva","Supervisor","Mecanica"}[i]);e.setSueldo(BigDecimal.valueOf(750000+i*150000));e.setActivo(true);e.setFechaContratacion(LocalDate.now().minusYears(i+1));e.setSucursalId(i+1);repo.save(e);}}};}
}

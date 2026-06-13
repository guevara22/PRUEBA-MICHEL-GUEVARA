package com.duoc.msclientes.config;
import com.duoc.msclientes.model.*;
import com.duoc.msclientes.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
@Configuration
public class DataLoader {
 @Bean CommandLineRunner cargarClientes(ClienteRepository clientes, DireccionRepository direcciones) {
  return args -> { if (clientes.count() == 0) {
   String[][] datos={{"Ana","Perez","ana@correo.cl","912345678"},{"Luis","Soto","luis@correo.cl","923456789"},{"Marta","Rojas","marta@correo.cl","934567890"}};
   for(int i=0;i<3;i++){ Cliente c=new Cliente(); c.setNombre(datos[i][0]);c.setApellido(datos[i][1]);c.setEmail(datos[i][2]);c.setTelefono(datos[i][3]);c.setEdad(25+i*5);c.setEsVip(i==0);c.setFechaAlta(LocalDate.now()); clientes.save(c);
    Direccion d=new Direccion();d.setCalle("Calle "+(i+1));d.setComuna("Santiago");d.setCiudad("Santiago");d.setCodigoPostal("8320000");d.setNumero(100+i);d.setPrincipal(true);d.setFechaRegistro(LocalDate.now());d.setCliente(c);direcciones.save(d); }
  }};
 }
}

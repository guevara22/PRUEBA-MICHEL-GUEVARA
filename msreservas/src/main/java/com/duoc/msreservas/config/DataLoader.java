package com.duoc.msreservas.config;

import com.duoc.msreservas.model.EstadoReserva;
import com.duoc.msreservas.repository.EstadoReservaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarEstadosReserva(EstadoReservaRepository repository) {
        return args -> {
            crearSiNoExiste(
                    repository,
                    "PENDIENTE",
                    "Reserva pendiente de confirmacion",
                    1,
                    true);
            crearSiNoExiste(
                    repository,
                    "CONFIRMADA",
                    "Reserva confirmada y vigente",
                    2,
                    true);
            crearSiNoExiste(
                    repository,
                    "FINALIZADA",
                    "Reserva completada correctamente",
                    3,
                    false);
        };
    }

    private void crearSiNoExiste(
            EstadoReservaRepository repository,
            String nombre,
            String descripcion,
            int prioridad,
            boolean permiteModificacion) {
        if (!repository.existsByNombreIgnoreCase(nombre)) {
            repository.save(crearEstado(nombre, descripcion, prioridad, permiteModificacion));
        }
    }

    private EstadoReserva crearEstado(
            String nombre,
            String descripcion,
            int prioridad,
            boolean permiteModificacion) {
        EstadoReserva estado = new EstadoReserva();
        estado.setNombre(nombre);
        estado.setDescripcion(descripcion);
        estado.setPrioridad(prioridad);
        estado.setPermiteModificacion(permiteModificacion);
        estado.setActivo(true);
        estado.setFechaCreacion(LocalDate.now());
        return estado;
    }
}

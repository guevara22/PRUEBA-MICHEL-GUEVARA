package com.duoc.msreservas.config;

import com.duoc.msreservas.model.EstadoReserva;
import com.duoc.msreservas.repository.EstadoReservaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarEstadosReserva(EstadoReservaRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        crearEstado("PENDIENTE", "Reserva pendiente de confirmacion", 1, true),
                        crearEstado("CONFIRMADA", "Reserva confirmada y vigente", 2, true),
                        crearEstado("FINALIZADA", "Reserva completada correctamente", 3, false)
                ));
            }
        };
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

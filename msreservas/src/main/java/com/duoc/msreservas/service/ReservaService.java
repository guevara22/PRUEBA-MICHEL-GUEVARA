package com.duoc.msreservas.service;

import com.duoc.msreservas.client.ClienteClient;
import com.duoc.msreservas.client.VehiculoClient;
import com.duoc.msreservas.client.VehiculoRemotoDTO;
import com.duoc.msreservas.dto.ReservaDTO;
import com.duoc.msreservas.dto.ReservaRequestDTO;
import com.duoc.msreservas.exception.ResourceNotFoundException;
import com.duoc.msreservas.mapper.ReservaMapper;
import com.duoc.msreservas.model.EstadoReserva;
import com.duoc.msreservas.model.Reserva;
import com.duoc.msreservas.repository.EstadoReservaRepository;
import com.duoc.msreservas.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReservaService {
    private final ReservaRepository repository;
    private final ReservaMapper mapper;
    private final EstadoReservaRepository estadoReservaRepository;
    private final ClienteClient clienteClient;
    private final VehiculoClient vehiculoClient;

    @Transactional(readOnly = true)
    public List<ReservaDTO> findAll() {
        log.info("Listando reservas");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ReservaDTO findById(Integer id) {
        log.info("Buscando Reserva con id {}", id);
        return mapper.toDTO(getEntity(id));
    }

    public ReservaDTO save(ReservaRequestDTO dto) {
        try {
            validarDatosRemotos(dto);
            EstadoReserva estado = getEstado(dto.getEstadoReservaId());
            Reserva saved = repository.save(mapper.toEntity(dto, estado));
            log.info("Reserva creada con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear Reserva: {}", ex.getMessage());
            throw ex;
        }
    }

    public ReservaDTO update(Integer id, ReservaRequestDTO dto) {
        validarDatosRemotos(dto);
        Reserva entity = getEntity(id);
        mapper.update(entity, dto, getEstado(dto.getEstadoReservaId()));
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
        log.info("Reserva eliminada con id {}", id);
    }

    private void validarDatosRemotos(ReservaRequestDTO dto) {
        if (!dto.getFechaFin().isAfter(dto.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
        clienteClient.findById(dto.getClienteId());
        VehiculoRemotoDTO vehiculo = vehiculoClient.findById(dto.getVehiculoId());
        if (!vehiculo.disponible()) {
            throw new IllegalArgumentException("El vehiculo seleccionado no esta disponible");
        }
    }

    private EstadoReserva getEstado(Integer id) {
        return estadoReservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoReserva", id));
    }

    private Reserva getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva", id));
    }
}

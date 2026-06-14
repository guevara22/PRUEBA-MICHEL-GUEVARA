package com.duoc.mspagos.service;

import com.duoc.mspagos.client.ReservaClient;
import com.duoc.mspagos.client.ReservaRemotaDTO;
import com.duoc.mspagos.dto.PagoDTO;
import com.duoc.mspagos.dto.PagoRequestDTO;
import com.duoc.mspagos.exception.ResourceNotFoundException;
import com.duoc.mspagos.mapper.PagoMapper;
import com.duoc.mspagos.model.Pago;
import com.duoc.mspagos.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PagoService {
    private final PagoRepository repository;
    private final PagoMapper mapper;
    private final ReservaClient reservaClient;

    @Transactional(readOnly = true)
    public List<PagoDTO> findAll() {
        log.info("Listando pagos");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public PagoDTO findById(Integer id) {
        log.info("Buscando Pago con id {}", id);
        return mapper.toDTO(getEntity(id));
    }

    public PagoDTO save(PagoRequestDTO dto) {
        try {
            validarReserva(dto);
            Pago saved = repository.save(mapper.toEntity(dto));
            log.info("Pago creado con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear Pago: {}", ex.getMessage());
            throw ex;
        }
    }

    public PagoDTO update(Integer id, PagoRequestDTO dto) {
        validarReserva(dto);
        Pago entity = getEntity(id);
        mapper.update(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
        log.info("Pago eliminado con id {}", id);
    }

    @Transactional(readOnly = true)
    public List<PagoDTO> buscarPorRango(BigDecimal montoMin, BigDecimal montoMax) {
        if (montoMin.compareTo(montoMax) > 0) {
            throw new IllegalArgumentException("El monto minimo no puede superar el monto maximo");
        }
        log.info("Buscando pagos entre {} y {}", montoMin, montoMax);
        return repository.buscarPorRango(montoMin, montoMax).stream()
                .map(mapper::toDTO)
                .toList();
    }

    private void validarReserva(PagoRequestDTO dto) {
        ReservaRemotaDTO reserva = reservaClient.findById(dto.getReservaId());
        if (dto.getMonto().compareTo(reserva.montoTotal()) > 0) {
            throw new IllegalArgumentException("El pago no puede superar el monto total de la reserva");
        }
    }

    private Pago getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago", id));
    }
}

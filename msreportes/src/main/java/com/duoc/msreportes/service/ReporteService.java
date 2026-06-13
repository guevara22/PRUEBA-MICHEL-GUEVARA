package com.duoc.msreportes.service;

import com.duoc.msreportes.client.PagoClient;
import com.duoc.msreportes.client.PagoRemotoDTO;
import com.duoc.msreportes.client.ReservaClient;
import com.duoc.msreportes.dto.ReporteDTO;
import com.duoc.msreportes.dto.ReporteRequestDTO;
import com.duoc.msreportes.exception.ResourceNotFoundException;
import com.duoc.msreportes.mapper.ReporteMapper;
import com.duoc.msreportes.model.Reporte;
import com.duoc.msreportes.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReporteService {
    private final ReporteRepository repository;
    private final ReporteMapper mapper;
    private final ReservaClient reservaClient;
    private final PagoClient pagoClient;

    @Transactional(readOnly = true)
    public List<ReporteDTO> findAll() {
        log.info("Listando reportes");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ReporteDTO findById(Integer id) {
        return mapper.toDTO(getEntity(id));
    }

    public ReporteDTO save(ReporteRequestDTO dto) {
        try {
            Reporte saved = repository.save(mapper.toEntity(dto));
            log.info("Reporte creado con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear Reporte: {}", ex.getMessage());
            throw ex;
        }
    }

    public ReporteDTO generarConsolidado() {
        int totalReservas = reservaClient.findAll().size();
        BigDecimal totalPagos = pagoClient.findAll().stream()
                .filter(PagoRemotoDTO::pagado)
                .map(PagoRemotoDTO::monto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Reporte reporte = new Reporte();
        reporte.setNombre("Consolidado " + LocalDateTime.now().toLocalDate());
        reporte.setTipo("CONSOLIDADO");
        reporte.setDescripcion("Consolidado generado desde ms-reservas y ms-pagos");
        reporte.setTotalReservas(totalReservas);
        reporte.setMontoTotalPagos(totalPagos);
        reporte.setGenerado(true);
        reporte.setFechaGeneracion(LocalDateTime.now());
        return mapper.toDTO(repository.save(reporte));
    }

    public ReporteDTO update(Integer id, ReporteRequestDTO dto) {
        Reporte entity = getEntity(id);
        mapper.update(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
    }

    private Reporte getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte", id));
    }
}

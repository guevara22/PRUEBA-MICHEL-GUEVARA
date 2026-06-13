package com.duoc.mspagos.mapper;

import com.duoc.mspagos.dto.PagoDTO;
import com.duoc.mspagos.dto.PagoRequestDTO;
import com.duoc.mspagos.model.Pago;

import org.springframework.stereotype.Component;

@Component
public class PagoMapper {
    public Pago toEntity(PagoRequestDTO dto) {
        Pago entity = new Pago();
        update(entity, dto);
        return entity;
    }

    public void update(Pago entity, PagoRequestDTO dto) {
        entity.setReservaId(dto.getReservaId());
        entity.setCodigoTransaccion(dto.getCodigoTransaccion());
        entity.setMonto(dto.getMonto());
        entity.setMedioPago(dto.getMedioPago());
        entity.setPagado(dto.isPagado());
        entity.setFechaPago(dto.getFechaPago());
        entity.setReferencia(dto.getReferencia());
    }

    public PagoDTO toDTO(Pago entity) {
        return PagoDTO.builder()
                .id(entity.getId())
                .reservaId(entity.getReservaId())
                .codigoTransaccion(entity.getCodigoTransaccion())
                .monto(entity.getMonto())
                .medioPago(entity.getMedioPago())
                .pagado(entity.isPagado())
                .fechaPago(entity.getFechaPago())
                .referencia(entity.getReferencia())
                .build();
    }
}

package com.duoc.msreportes.client;
import java.math.BigDecimal;
public record PagoRemotoDTO(Integer id, BigDecimal monto, boolean pagado) {}

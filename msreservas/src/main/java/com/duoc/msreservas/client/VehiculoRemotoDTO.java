package com.duoc.msreservas.client;
import java.math.BigDecimal;
public record VehiculoRemotoDTO(Integer id, String patente, BigDecimal precioArriendoDiario, boolean disponible) {}

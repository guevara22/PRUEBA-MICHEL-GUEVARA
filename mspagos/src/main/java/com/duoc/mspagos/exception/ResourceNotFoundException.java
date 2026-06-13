package com.duoc.mspagos.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String recurso, Integer id) {
        super(recurso + " no encontrado con id " + id);
    }
}

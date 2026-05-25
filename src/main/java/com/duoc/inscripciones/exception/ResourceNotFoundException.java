package com.duoc.inscripciones.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException curso(Long id) {
        return new ResourceNotFoundException("Curso with id " + id + " was not found");
    }
}

package com.capas.parcial2_capas.Exception;
//package com.uca.pncsegundoparcialveterinaria.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
package com.capas.parcial2_capas.Exception;

//package com.uca.pncsegundoparcialveterinaria.exception;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
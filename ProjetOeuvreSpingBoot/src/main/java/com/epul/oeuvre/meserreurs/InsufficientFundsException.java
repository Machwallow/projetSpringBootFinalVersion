package com.epul.oeuvre.meserreurs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException() {
        super("Vous n'avez pas les fonds nécessaires pour faire cet achat");
    }
}

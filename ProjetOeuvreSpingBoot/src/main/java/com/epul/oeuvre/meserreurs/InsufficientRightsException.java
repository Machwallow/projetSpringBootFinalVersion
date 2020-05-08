package com.epul.oeuvre.meserreurs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InsufficientRightsException extends RuntimeException{

    public InsufficientRightsException() {
        super("Vous n'avez pas les droits pour effectuer cette action");
    }
}
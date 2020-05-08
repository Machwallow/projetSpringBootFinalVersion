package com.epul.oeuvre.meserreurs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WrongAdherentException extends RuntimeException{

    public WrongAdherentException(Integer idAdh){
        super(String.format("Adherent " + idAdh + " doesn't exist"));
    }

    public WrongAdherentException(String prenom, String nom){
        super(String.format("Adherent " + nom + " " + prenom+ " doesn't exist"));
    }

}

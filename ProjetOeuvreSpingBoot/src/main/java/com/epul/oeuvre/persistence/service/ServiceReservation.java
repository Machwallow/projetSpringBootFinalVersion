package com.epul.oeuvre.persistence.service;

import com.epul.oeuvre.domains.*;
import com.epul.oeuvre.persistence.repositories.RepositoryEntityReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;


@Service
public class ServiceReservation {

    @Autowired
    private RepositoryEntityReservation repositoryEntityReservation;

    public void addReservation(EntityOeuvrevente oeuvrevente, EntityAdherent adherent, EntityProprietaire proprietaire){


        Date now = new Date(java.lang.System.currentTimeMillis());

        repositoryEntityReservation.addReservation(oeuvrevente.getIdOeuvrevente(),
                adherent.getIdAdherent(),
                now,
                "en attente",
                proprietaire.getIdProprietaire()
                );

        /*EntityReservation temp = new EntityReservation();
        temp.setOeuvreventeByIdOeuvrevente(oeuvrevente);
        temp.setAdherentByIdAdherent(adherent);
        temp.setProprietaireByIdProprietaire(proprietaire);
        temp.setStatut("en attente");

        temp.setDateReservation(now);

        repositoryEntityReservation.save(temp);*/
    }

    @Transactional
    public void deleteReservation(EntityAdherent adherent, EntityOeuvrevente oeuvrevente){
        repositoryEntityReservation.deleteByAdherentByIdAdherentAndOeuvreventeByIdOeuvrevente(adherent,oeuvrevente);
    }

    @Transactional
    public void updateStatut(EntityAdherent adherent, EntityOeuvrevente oeuvrevente, String statut){
        repositoryEntityReservation.updateStatutReservation(oeuvrevente.getIdOeuvrevente(),
                adherent.getIdAdherent(),
                statut);
    }

    public List<EntityReservation> getReservationsByAdherent(EntityAdherent adherent){
        return repositoryEntityReservation.findAllByAdherentByIdAdherent(adherent);
    }

    public List<EntityReservation> getReservationsByProprietaire(EntityProprietaire proprietaire){
        return repositoryEntityReservation.findAllByProprietaireByIdProprietaire(proprietaire);
    }

}

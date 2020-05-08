package com.epul.oeuvre.persistence.service;

import com.epul.oeuvre.domains.EntityOeuvrevente;
import com.epul.oeuvre.domains.EntityProprietaire;
import com.epul.oeuvre.persistence.repositories.RepositoryEntityOeuvrevente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceOeuvrevente {

    @Autowired
    private RepositoryEntityOeuvrevente repositoryEntityOeuvrevente;

    public List<EntityOeuvrevente> getOeuvres(){
        return repositoryEntityOeuvrevente.findAll(sortById());
    }

    public List<EntityOeuvrevente> getOeuvresLibres(){
        return repositoryEntityOeuvrevente.findAllByEtatOeuvreventeEquals("L");
    }

    public List<EntityOeuvrevente> getOeuvresReservees(){
        return repositoryEntityOeuvrevente.findAllByEtatOeuvreventeEquals("R");
    }

    public List<EntityOeuvrevente> getOeuvresVendues(){
        return repositoryEntityOeuvrevente.findAllByEtatOeuvreventeEquals("V");
    }

    public EntityOeuvrevente getOeuvreById(Integer id){
        return repositoryEntityOeuvrevente.findByIdOeuvrevente(id);
    }

    public List<EntityOeuvrevente> getOeuvresByProprietaire(EntityProprietaire proprietaire){
        return repositoryEntityOeuvrevente.findAllByProprietaireByIdProprietaire(proprietaire);
    }

    public void updateEtatOeuvre(Integer id, String etat){
        EntityOeuvrevente temp = getOeuvreById(id);
        temp.setEtatOeuvrevente(etat);
        repositoryEntityOeuvrevente.save(temp);
    }

    public void updateProprietaireOeuvre(Integer id, EntityProprietaire proprietaire){
        EntityOeuvrevente temp = getOeuvreById(id);
        temp.setProprietaireByIdProprietaire(proprietaire);
        repositoryEntityOeuvrevente.save(temp);
    }

    @Transactional
    public void addOeuvrevente(String titre, double prix, EntityProprietaire proprietaire){
        EntityOeuvrevente temp = new EntityOeuvrevente();
        temp.setTitreOeuvrevente(titre);
        temp.setPrixOeuvrevente(prix);
        temp.setProprietaireByIdProprietaire(proprietaire);
        temp.setEtatOeuvrevente("L");
        repositoryEntityOeuvrevente.save(temp);
    }

    @Transactional
    public void updateOeuvrevente(Integer id, String titre, double prix, EntityProprietaire proprietaire){
        EntityOeuvrevente temp = repositoryEntityOeuvrevente.findByIdOeuvrevente(id);
        temp.setTitreOeuvrevente(titre);
        temp.setPrixOeuvrevente(prix);
        temp.setProprietaireByIdProprietaire(proprietaire);
        repositoryEntityOeuvrevente.save(temp);
    }

    @Transactional
    public void deleteOeuvreById(Integer id){
        repositoryEntityOeuvrevente.deleteByIdOeuvrevente(id);
    }

    private Sort sortById(){
        return new Sort(Sort.Direction.ASC,"idOeuvrevente");
    }
}

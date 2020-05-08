package com.epul.oeuvre.persistence.service;

import com.epul.oeuvre.domains.EntityAdherent;
import com.epul.oeuvre.domains.EntityOeuvrepret;
import com.epul.oeuvre.domains.EntityProprietaire;
import com.epul.oeuvre.persistence.repositories.RepositoryEntityOeuvrepret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceOeuvrepret {

    @Autowired
    private RepositoryEntityOeuvrepret repositoryEntityOeuvrepret;

    public List<EntityOeuvrepret> getOeuvres(){
        return repositoryEntityOeuvrepret.findAll(sortByNum());
    }

    @Transactional
    public void changeEtatOeuvrepret(Integer idOeuvre, EntityAdherent adherent, String etat) throws Exception{

        EntityOeuvrepret temp = getOeuvreById(idOeuvre);

        if(temp != null){
            if(etat.equals("emprunter")){
                //temp.setIdAdherent(adherent.getIdAdherent());
                temp.setAdherentByIdAdherent(adherent);
            }
            else if(etat.equals("rendre"))
                //temp.setIdAdherent(null);
                temp.setAdherentByIdAdherent(null);
        }
        else
            throw new Exception("WAAAAAAAAAAAAAAAAAAAH");

        repositoryEntityOeuvrepret.save(temp);

    }

    public EntityOeuvrepret getOeuvreById(Integer id){
        return repositoryEntityOeuvrepret.findByIdOeuvrepret(id);
    }

    public List<EntityOeuvrepret> getOeuvresByProprietaire(EntityProprietaire proprietaire){
        return repositoryEntityOeuvrepret.findAllByProprietaireByIdProprietaire(proprietaire);
    }

    public List<EntityOeuvrepret> getOeuvresByAdherent(EntityAdherent adherent){
        return repositoryEntityOeuvrepret.findAllByAdherentByIdAdherent(adherent);
    }

    @Transactional
    public void updateOeuvre(Integer id, String titre, EntityProprietaire proprietaire, EntityAdherent adherent){
        EntityOeuvrepret temp = getOeuvreById(id);
        temp.setTitreOeuvrepret(titre);
        temp.setProprietaireByIdProprietaire(proprietaire);
        temp.setAdherentByIdAdherent(adherent);
        repositoryEntityOeuvrepret.save(temp);
    }

    @Transactional
    public void addOeuvre(String titre, EntityProprietaire proprietaire, EntityAdherent adherent){
        EntityOeuvrepret temp = new EntityOeuvrepret();
        temp.setTitreOeuvrepret(titre);
        temp.setProprietaireByIdProprietaire(proprietaire);
        temp.setAdherentByIdAdherent(adherent);
        repositoryEntityOeuvrepret.save(temp);
    }

    @Transactional
    public void deleteOeuvreById(Integer id){
        repositoryEntityOeuvrepret.deleteEntityOeuvrepretByIdOeuvrepret(id);
    }

    private Sort sortByNum() {
        return new Sort(Sort.Direction.ASC, "idOeuvrepret");
    }
}

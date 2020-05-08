package com.epul.oeuvre.persistence.service;

import com.epul.oeuvre.domains.EntityAdherent;
import com.epul.oeuvre.meserreurs.WrongAdherentException;
import com.epul.oeuvre.persistence.repositories.RepositoryEntityAdherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//POUR FAIRE DES NAMED QUERIES
//@NamedQuery(name="test", query = "select adh from EntityAdherent adh")

@Service
public class ServiceAdherent {

    @Autowired
    private RepositoryEntityAdherent repositoryEntityAdherent;


    public EntityAdherent getAdherent(Integer id) throws WrongAdherentException {
        try {
            return repositoryEntityAdherent.findByIdAdherent(id);
        } catch(Exception e) {
            throw new WrongAdherentException(id);
        }
    }

    public EntityAdherent getAdherent(String prenom, String nom) throws WrongAdherentException{
        try {
            return repositoryEntityAdherent.findEntityAdherentByPrenomAdherentAndNomAdherent(prenom,nom);
        } catch(Exception e) {
            throw new WrongAdherentException(prenom, nom);
        }
    }

    public List<EntityAdherent> getAllAdherents(){
        return repositoryEntityAdherent.findAll(sortByNumAdh());
    }

    public EntityAdherent createAdherent(String nom, String prenom, String ville){
        EntityAdherent temp = new EntityAdherent();
        temp.setNomAdherent(nom);
        temp.setPrenomAdherent(prenom);
        temp.setVilleAdherent(ville);
        return repositoryEntityAdherent.save(temp);
    }

    public EntityAdherent updateAdherent(Integer idAdherent, String nom, String prenom, String ville){
        EntityAdherent temp = getAdherent(idAdherent);
        temp.setPrenomAdherent(prenom);
        temp.setNomAdherent(nom);
        temp.setVilleAdherent(ville);
        return repositoryEntityAdherent.save(temp);
    }

    @Transactional
    public int deleteAdherent(Integer id){
        return repositoryEntityAdherent.deleteEntityAdherentByIdAdherent(id);
    }

    private Sort sortByNumAdh(){
        return new Sort(Sort.Direction.ASC, "idAdherent");
    }
}

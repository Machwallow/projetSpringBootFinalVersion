package com.epul.oeuvre.persistence.service;

import com.epul.oeuvre.domains.EntityProprietaire;
import com.epul.oeuvre.persistence.repositories.RepositoryEntityProprietaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProprietaire {

    @Autowired
    RepositoryEntityProprietaire repositoryEntityProprietaire;

    public List<EntityProprietaire> getAllProprietaire(){
        return repositoryEntityProprietaire.findAll(sortByNum());
    }

    public EntityProprietaire getProprietaire(Integer id){
        return repositoryEntityProprietaire.getByIdProprietaire(id);
    }

    public void addProprietaire(Integer id,String nom, String prenom){

        EntityProprietaire temp = new EntityProprietaire();
        temp.setIdProprietaire(id);
        temp.setNomProprietaire(nom);
        temp.setPrenomProprietaire(prenom);
        repositoryEntityProprietaire.save(temp);

    };

    private Sort sortByNum(){
        return new Sort(Sort.Direction.ASC, "idProprietaire");
    }

}

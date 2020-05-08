package com.epul.oeuvre.persistence.service;

import com.epul.oeuvre.domains.EntityUtilisateur;
import com.epul.oeuvre.persistence.repositories.RepositoryEntityUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUtilisateur {

    @Autowired
    private RepositoryEntityUtilisateur repositoryEntityUtilisateur;

    public EntityUtilisateur getUtilisateurByNom(String nom) throws Exception{
        try {
            return repositoryEntityUtilisateur.findByNomUtil(nom);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void updateSolde(String nom, Double solde) throws Exception{
        try {
            EntityUtilisateur temp = getUtilisateurByNom(nom);
            temp.setSolde(solde);
            repositoryEntityUtilisateur.save(temp);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

}

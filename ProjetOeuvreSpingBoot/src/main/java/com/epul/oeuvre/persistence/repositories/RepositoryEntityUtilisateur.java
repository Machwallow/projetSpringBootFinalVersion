package com.epul.oeuvre.persistence.repositories;

import com.epul.oeuvre.domains.EntityUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEntityUtilisateur extends JpaRepository<EntityUtilisateur, Integer>  {

    EntityUtilisateur findByNomUtil(String NomUtil);

}


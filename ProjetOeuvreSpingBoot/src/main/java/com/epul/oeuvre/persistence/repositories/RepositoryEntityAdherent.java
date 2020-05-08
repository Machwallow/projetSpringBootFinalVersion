package com.epul.oeuvre.persistence.repositories;


import com.epul.oeuvre.domains.EntityAdherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEntityAdherent extends JpaRepository<EntityAdherent, Integer> {

    EntityAdherent findEntityAdherentByPrenomAdherentAndNomAdherent(String prenom, String nom);

    EntityAdherent findByIdAdherent(Integer id);

    int deleteEntityAdherentByIdAdherent(Integer id);

}


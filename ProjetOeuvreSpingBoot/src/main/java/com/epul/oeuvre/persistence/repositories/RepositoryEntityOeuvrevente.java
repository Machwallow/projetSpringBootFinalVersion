package com.epul.oeuvre.persistence.repositories;

import com.epul.oeuvre.domains.EntityOeuvrevente;
import com.epul.oeuvre.domains.EntityProprietaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryEntityOeuvrevente extends JpaRepository<EntityOeuvrevente,Integer> {

    List<EntityOeuvrevente> findAllByEtatOeuvreventeEquals(String etat);

    List<EntityOeuvrevente> findAllByProprietaireByIdProprietaire(EntityProprietaire proprietaire);

    EntityOeuvrevente findByIdOeuvrevente(Integer id);

    void deleteByIdOeuvrevente(Integer id);


}

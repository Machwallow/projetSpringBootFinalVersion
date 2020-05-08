package com.epul.oeuvre.persistence.repositories;

import com.epul.oeuvre.domains.EntityAdherent;
import com.epul.oeuvre.domains.EntityOeuvrepret;
import com.epul.oeuvre.domains.EntityProprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryEntityOeuvrepret extends JpaRepository<EntityOeuvrepret, Integer> {

    EntityOeuvrepret findByIdOeuvrepret(Integer id);

    void deleteEntityOeuvrepretByIdOeuvrepret(Integer id);

    List<EntityOeuvrepret>  findAllByProprietaireByIdProprietaire(EntityProprietaire proprietaire);

    List<EntityOeuvrepret> findAllByAdherentByIdAdherent(EntityAdherent adherent);
}

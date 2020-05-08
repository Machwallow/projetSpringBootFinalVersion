package com.epul.oeuvre.persistence.repositories;

import com.epul.oeuvre.domains.EntityProprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEntityProprietaire extends JpaRepository<EntityProprietaire, Integer> {

    EntityProprietaire getByIdProprietaire(Integer id);


}

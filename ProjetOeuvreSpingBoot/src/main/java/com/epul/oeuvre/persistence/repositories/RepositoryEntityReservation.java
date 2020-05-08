package com.epul.oeuvre.persistence.repositories;

import com.epul.oeuvre.domains.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface RepositoryEntityReservation extends JpaRepository<EntityReservation, EntityReservationPK> {

    //obligatoire, je n'ai pas réussi avec save, avec une custom query, aucun soucis
    @Modifying
    @Query(value = "insert into Reservation values (:idOeuvre, :idAdherent, :date, :statut, :idProprietaire)", nativeQuery = true)
    @Transactional
    void addReservation(@Param("idOeuvre") Integer idOeuvre, @Param("idAdherent") Integer idAdherent,
                        @Param("date") Date date, @Param("statut") String statut,
                        @Param("idProprietaire") Integer idProprietaire);

    @Modifying
    @Query(value = "update reservation r set r.statut = :statut where r.id_adherent = :idAdherent and r.id_oeuvrevente = :idOeuvre ", nativeQuery = true)
    @Transactional
    void updateStatutReservation(@Param("idOeuvre") Integer idOeuvre, @Param("idAdherent") Integer idAdherent
                        ,@Param("statut") String statut);

    void deleteByAdherentByIdAdherentAndOeuvreventeByIdOeuvrevente(EntityAdherent adherent, EntityOeuvrevente oeuvrevente);

    List<EntityReservation> findAllByAdherentByIdAdherent(EntityAdherent adherent);

    List<EntityReservation> findAllByProprietaireByIdProprietaire(EntityProprietaire proprietaire);

    EntityReservation findByAdherentByIdAdherentAndOeuvreventeByIdOeuvrevente(EntityAdherent adherent, EntityOeuvrevente oeuvrevente);
}

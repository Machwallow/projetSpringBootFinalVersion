package com.epul.oeuvre.domains;


import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "reservation", schema = "baseoeuvre", catalog = "")
@IdClass(EntityReservationPK.class)
public class EntityReservation {

    private Integer idOeuvrevente;
    private Integer idAdherent;
    private Date dateReservation;
    private String statut;
    private EntityOeuvrevente oeuvreventeByIdOeuvrevente;
    private EntityAdherent adherentByIdAdherent;
    private EntityProprietaire proprietaireByIdProprietaire;

    /*@Id
    @Column(name = "id_oeuvrevente", nullable = false)
    public Integer getIdOeuvrevente() {
        return idOeuvrevente;
    }

    public void setIdOeuvrevente(Integer idOeuvrevente) {
        this.idOeuvrevente = idOeuvrevente;
    }

    @Id
    @Column(name = "id_adherent", nullable = false)
    public Integer getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(Integer idAdherent) {
        this.idAdherent = idAdherent;
    }*/

    @Basic
    @Column(name = "date_reservation", nullable = false)
    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Basic
    @Column(name = "statut", nullable = false, length = 20)
    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityReservation that = (EntityReservation) o;
        return Objects.equals(idOeuvrevente, that.idOeuvrevente) &&
                Objects.equals(idAdherent, that.idAdherent) &&
                Objects.equals(dateReservation, that.dateReservation) &&
                Objects.equals(statut, that.statut);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOeuvrevente, idAdherent, dateReservation, statut);
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "id_oeuvrevente", referencedColumnName = "id_oeuvrevente", nullable = false)
    public EntityOeuvrevente getOeuvreventeByIdOeuvrevente() {
        return oeuvreventeByIdOeuvrevente;
    }

    public void setOeuvreventeByIdOeuvrevente(EntityOeuvrevente oeuvreventeByIdOeuvrevente) {
        this.oeuvreventeByIdOeuvrevente = oeuvreventeByIdOeuvrevente;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "id_adherent", referencedColumnName = "id_adherent", nullable = false)
    public EntityAdherent getAdherentByIdAdherent() {
        return adherentByIdAdherent;
    }

    public void setAdherentByIdAdherent(EntityAdherent adherentByIdAdherent) {
        this.adherentByIdAdherent = adherentByIdAdherent;
    }

    @ManyToOne
    @JoinColumn(name = "id_proprietaire", referencedColumnName = "id_proprietaire", nullable = false)
    public EntityProprietaire getProprietaireByIdProprietaire() {
        return proprietaireByIdProprietaire;
    }

    public void setProprietaireByIdProprietaire(EntityProprietaire proprietaireByIdProprietaire) {
        this.proprietaireByIdProprietaire = proprietaireByIdProprietaire;
    }
}
